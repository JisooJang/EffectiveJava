package observer;

import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
https://youtu.be/DChIxy9g19o
토비의 봄 TV 6회 스프링 리액티브 프로그래밍 (2) - Reactive Streams - Operators 강의 내용 실습

Operator : 데이터 흐름을 정의할 수 있음.
pub -> [Data1] -> mapPub -> [Data2] ->  -> logSub
 */
public class PubSubOperator {
    public static void main(String[] args) {
        List<Integer> iter = Stream.iterate(1, i -> i+1).limit(10).collect(Collectors.toList());
        Publisher<Integer> pub = iterPub(iter);
        Publisher<Integer> mapPub = mapPub(pub, i -> i * 10); // 중개 1
        Publisher<Integer> sumPub = sumPub(pub);
        Publisher<Integer> reducePub = reducePub(pub, 0, (a,b) -> a+b);
        reducePub.subscribe(logSub());
    }

    // 중개자 Publisher (데이터 변환). Function<Argument, ReturnType>
    private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                pub.subscribe(new DelegateSub(subscriber) {
                    @Override
                    public void onNext(Integer i) {
                        sub.onNext(f.apply(i));
                    }
                });
            }
        };
    }

    private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                pub.subscribe(new DelegateSub(subscriber) {
                    int sum = 0;
                    @Override public void onNext(Integer i) {
                        sum += i;
                    }

                    @Override public void onComplete() {
                        sub.onNext(sum);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static Publisher<Integer> reducePub(Publisher<Integer> pub, int start, BiFunction<Integer, Integer, Integer> f) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                pub.subscribe(new DelegateSub(subscriber) {
                    int result = start;
                    @Override public void onNext(Integer i) {
                        result = f.apply(result, i);
                    }

                    @Override public void onComplete() {
                        subscriber.onNext(result);
                        subscriber.onComplete();
                    }
                });
            }
        };
    }

    private static Subscriber<Integer> logSub() {
        return new Subscriber<Integer>() {
                @Override
                public void onSubscribe(Subscription subscription) {
                    subscription.request(Long.MAX_VALUE);
                    System.out.println("onSubscribe");
                }

                @Override
                public void onNext(Integer item) {
                    System.out.println("onNext:" + item);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("onError");
                }

                @Override
                public void onComplete() {
                    System.out.println("onComplete");
                }
            };
    }

    private static Publisher<Integer> iterPub(List<Integer> iter) {
        return new Publisher<Integer>() {
                @Override
                public void subscribe(Subscriber<? super Integer> subscriber) {
                    subscriber.onSubscribe(new Subscription() {
                        @Override
                        public void request(long n) {
                            try {
                                iter.forEach(s->subscriber.onNext(s));
                                subscriber.onComplete();
                            } catch(Throwable t) {
                                subscriber.onError(t);
                            }
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                }
            };
    }
}
