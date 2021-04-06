package ru.realityfamily.automattask.Models;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.realityfamily.automattask.MainActivity;

public class Student {
    private Handler handlerupdate;
    private final String name;
    private List<IProduct> cart = new ArrayList<>();
    private final int cartCount;
    private final Automat automat;
    private final Student st;
    //private StudentTask task;
    //private AnotherRunnable task2;
    Thread thread;

    public Student(int name, int cartCount, Automat automat) {
        this.name = "Студент №" + name;
        this.cartCount = cartCount;
        this.automat = automat;
        st = this;
        //this.task = new StudentTask(automat, this);
        this.thread=new Thread(new AnotherRunnable());
        handlerupdate = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                MainActivity.getInstance().UpdateData(automat, st);
            }

        };
    }

    public void ChooseProduct() throws InterruptedException {
        while (cart.size() < cartCount) {
            IProduct product = automat.GetProduct();
            if (product != null) {
                TimeUnit.SECONDS.sleep(1);
                cart.add(product);
            }
        }
    }

    public double CartCost() {
        double finalCost = 0;
        for (IProduct product : cart) {
            finalCost += product.getCost();
        }
        return finalCost;
    }

    public void PayForCart() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
    /*
    public void StartTask() {
        if (task == null) return;
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }*/

    public void StartTask2() {
        if (thread == null) return;
        thread.start();
    }

    public String getName() {
        return name;
    }

    public List<IProduct> getCart() {
        return cart;
    }

    public int getAutomatName() {
        return automat.getName();
    }
    /*
    public AsyncTask.Status getTaskStatus() {
        return task.getStatus();
    }
    */

    class AnotherRunnable implements Runnable {

        public AnotherRunnable() {
        }
        @Override
        public void run() {
            synchronized (automat) {
                publishProgress();
                automat.setStatus(Automat.AutomatStatus.Client_Choosing);
                publishProgress();
                try {
                    st.ChooseProduct();
                    publishProgress();
                    automat.setStatus(Automat.AutomatStatus.Client_Paying);
                    publishProgress();
                    st.PayForCart();
                    automat.setStatus(Automat.AutomatStatus.Giving_Products);
                    publishProgress();
                    automat.GiveProducts();
                    automat.setStatus(Automat.AutomatStatus.Waiting);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        public void publishProgress(){
            Message message = new Message();
            handlerupdate.sendMessage(message);
        }
    }
    /*class StudentTask extends AsyncTask<Void, Void, Void> {
        final Automat automat;
        final Student student;

        public StudentTask(Automat automat, Student student) {
            this.automat = automat;
            this.student = student;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            synchronized (automat) {
                publishProgress();
                automat.setStatus(Automat.AutomatStatus.Client_Choosing);
                publishProgress();
                try {
                    student.ChooseProduct();
                    publishProgress();
                    automat.setStatus(Automat.AutomatStatus.Client_Paying);
                    publishProgress();
                    student.PayForCart();
                    automat.setStatus(Automat.AutomatStatus.Giving_Products);
                    publishProgress();
                    automat.GiveProducts();
                    automat.setStatus(Automat.AutomatStatus.Waiting);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            MainActivity.getInstance().UpdateData(automat, student);
        }
    }*/
}
