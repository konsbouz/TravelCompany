package com.travelcompany.eshop.domain;

public enum Category {

        Individual(-0.2),
        Business(0.1);


        private double discount;
        Category(double discount){
                this.discount = discount;
        }

        public double getDiscount() {
                return discount;
        }
}


