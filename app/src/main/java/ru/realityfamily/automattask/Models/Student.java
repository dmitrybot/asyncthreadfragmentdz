package ru.realityfamily.automattask.Models;

import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ru.realityfamily.automattask.MainActivity;

public class Student {
    private final String name;
    private List<IProduct> cart;
    private final int cartCount;
    private Automat automat;

    public Student(int name, int cartCount, Automat automat) {
        this.name = "Студент №" + name;
        this.cartCount = cartCount;
        this.automat = automat;
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

    public String getName() {
        return name;
    }

    public List<IProduct> getCart() {
        return cart;
    }

    class StudentTask extends AsyncTask<Void, Void, Void> {
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
    }
}
