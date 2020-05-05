package com.demo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            /**
             * 监听器得到事件
             * @param event
             */
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println("监听器得到事件：" + event.getSource() + "@" + event.getApplicationContext());
            }
        });
        context.refresh();
        //发布事件，即触发事件发生
        context.publishEvent(new MyApplicationEvent(context, "Hello,World"));
        context.publishEvent(new MyApplicationEvent(context, 1));
        context.publishEvent(new MyApplicationEvent(context, System.currentTimeMillis()));

    }

    private static class MyApplicationEvent extends ApplicationEvent {

        private final ApplicationContext context;

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationEvent(ApplicationContext context, Object source) {
            super(source);
            this.context = context;
        }

        public ApplicationContext getApplicationContext() {
            return this.context;
        }
    }
}
