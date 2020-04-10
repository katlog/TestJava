package org.person.dfw.java8;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * Created by fw on 2019/11/4
 */
public class TestCompletableFuture {


    @Test
    public void future(){
        //←─创建Executor-Service，通过它你可向线程池提交任务
        ExecutorService executor = Executors.newCachedThreadPool();
        //←─向Executor-Service提交一个Callable对象
        //←─以异步方式在新的线程中执行耗时的操作
        Future<Double> future = executor.submit(this::doSomeLongComputation);
        doSomethingElse();    //←─异步操作进行的同时，你可做其他的事情
        try {
            //←─获取异步操作的结果，若最终被阻塞，无法得到结果，则在最多等待1秒钟后退出
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成前超过已过期
        }
    }
    private void doSomethingElse() {
    }
    private Double doSomeLongComputation() {
        return null;
    }

    /** ---------------------------创建异步任务------------------------------------ */
    /** 无返回值 */
    @Test
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });
        // 返回 null
        Void x = future.get();
        System.out.println(x);
    }

    /** 支持返回值 */
    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);
    }

    /** ---------------------------创建异步任务------------------------------------ */




    /** ---------------------------计算完成时对结果的处理 whenComplete/exceptionally/handle------------------------------------ */

    /** 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action */
    @Test
    public void whenComplete() throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                System.out.println(12/0);
            }
            System.out.println("run end ...");
        });

        future.whenComplete((t, action) -> System.out.println("执行完成！"));
        future.exceptionally(t -> {
            System.out.println("执行失败！"+t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     *  */
    @Test
    public void handle () throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i= 10/0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if(throwable==null){
                result = param * 2;
            }else{
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future.get());
    }

    /** ---------------------------计算完成时对结果的处理 whenComplete/exceptionally/handle------------------------------------ */




    /** ---------------------------结果转换处理和纯消费 thenApply/thenAccept/thenAcceptBoth/thenRun ------------------------------------ */

    /** 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化 */
    @Test
    public void thenApply () throws ExecutionException, InterruptedException {

        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(800)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result1="+result);
            return result;
        }).thenApply(t -> {
            System.out.println("step into then apply");
            long result = t*10;
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result2="+result);
            return result;
        });

        long result = future.get();
        System.out.println(result);
    }

    @Test
    public void thenApply1() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            try {
                //休眠200秒
                Thread.sleep(200000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("supplyAsync " + Thread.currentThread().getName());
            return "hello ";
        },executorService).thenAccept(s -> {
            try {
                System.out.println("world " + s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(Thread.currentThread().getName());
        while (true) {
            if (cf.isDone()) {
                System.out.println("CompletedFuture...isDown");
                break;
            }
        }
    }


    /** 接收任务的处理结果，并消费处理，无返回结果。
     *  该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。并没有后续的输错操作
     * */
    @Test
    public void thenAccept () throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return new Random().nextInt(10);
                })
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("run over ..."));
        future.get();
    }

    /** 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun  */
    @Test
    public void thenRun () throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future =
                CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                        .thenRun(() -> {
                            System.out.println("thenRun ...");
                        });
        future.get();
    }
    /** ---------------------------结果转换处理和纯消费 thenApply/thenAccept/thenAcceptBoth/thenRun ------------------------------------ */





    /** ---------------------------组合两个 thenCompose/thenCombine------------------------------------ */

    /** 会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理 */
    @Test
    public void thenCombine () throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t+" "+u);
        System.out.println(result.get());
    }

    /** 允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。 */
    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1="+t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param *2;
            System.out.println("t2="+t);
            return t;
        }));
        System.out.println("thenCompose result : "+f.get());

    }

    /** 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗 */
    @Test
    public void thenAcceptBoth() throws InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1="+t+";f2="+u+";"));
        TimeUnit.SECONDS.sleep(3);
    }

    /** ---------------------------组合两个 thenCompose/thenCombine------------------------------------ */





    /** --------------------------- acceptEither / applyToEither------------------------------------ */

    /** 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。 */
    @Test
    public void applyToEither () throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> dep1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3000);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        System.out.println("dep1 = " + dep1);
        CompletableFuture<Integer> dep2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3000);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        System.out.println("dep2 = " + dep2);
        CompletableFuture<Integer> result = dep1.applyToEither(dep2, t -> {
            System.out.println(t);
            return t * 2;
        });
        System.out.println("result = " + result);
        System.out.println(result.get());
    }

    /** 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。 */
    @Test
    public void acceptEither () throws InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(4) +1;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(4)+1;
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.acceptEither(f2, System.out::println);
        TimeUnit.SECONDS.sleep(4L);
    }
    /** --------------------------- acceptEither / applyToEither------------------------------------ */




    /** --------------------------- runAfterEither / runAfterBoth------------------------------------ */

    /** 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable） */
    @Test
    public void runAfterEither (){
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.runAfterEither(f2, () -> System.out.println("上面有一个已经完成了。"));
    }

    /** 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable） */
    @Test
    public void runAfterBoth(){
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
            return t;
        });
        f1.runAfterBoth(f2, () -> System.out.println("上面两个任务都执行完成了。"));
    }
    /** --------------------------- runAfterEither / runAfterBoth------------------------------------ */





    /** ---------------------------取多个任务的结果 allOf/anyOf ------------------------------------ */

    /**  */
    @Test
    public void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(1);
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2);
                }))
                .get();
        System.out.println("true = " + true);
    }


    @Test
    public void anyOf() throws ExecutionException, InterruptedException {
        Object obj = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("run 1  ");
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }),
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("run 2  ");
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                })).get();
        System.out.println(obj);
    }

    /** ---------------------------取多个任务的结果 allOf/anyOf ------------------------------------ */
}

