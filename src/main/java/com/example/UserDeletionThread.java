package com.example;

import com.example.service.AdminService;
import com.example.service.PaymentService;

public class UserDeletionThread extends Thread {

    private PaymentService paymentService = new PaymentService();
    private AdminService adminService = new AdminService();

    public UserDeletionThread(String name) {
        super(name);
    }

    public void run() {
        while (true) {
            System.out.println("START USER DELETION");
            adminService.updateAdminCards("1111111111111111");
            paymentService.removeAllUserWhoDoNotPaid();
            System.out.println("FINISH USER DELETION");
            try {
                Thread.sleep(86400000);
                //Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
