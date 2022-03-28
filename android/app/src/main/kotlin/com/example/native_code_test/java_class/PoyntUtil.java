//package com.example.native_code_test;
//
//import android.content.res.Resources;
//import android.graphics.BitmapFactory;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.net.URL;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateExpiredException;
//import java.security.cert.CertificateNotYetValidException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.UUID;
//
//import javax.net.ssl.HttpsURLConnection;
//
//import co.poynt.api.model.Card;
//import co.poynt.api.model.CurrencyAmount;
//import co.poynt.api.model.Fee;
//import co.poynt.api.model.FundingSource;
//import co.poynt.api.model.FundingSourceType;
//import co.poynt.api.model.Order;
//import co.poynt.api.model.OrderAmounts;
//import co.poynt.api.model.OrderItem;
//import co.poynt.api.model.OrderItemStatus;
//import co.poynt.api.model.OrderStatus;
//import co.poynt.api.model.OrderStatuses;
//import co.poynt.api.model.Product;
//import co.poynt.api.model.ProductType;
//import co.poynt.api.model.SelectableValue;
//import co.poynt.api.model.SelectableVariation;
//import co.poynt.api.model.Transaction;
//import co.poynt.api.model.TransactionAmounts;
//import co.poynt.api.model.UnitOfMeasure;
//import co.poynt.api.model.Variant;
//import co.poynt.os.model.PrintedReceipt;
//import co.poynt.os.model.PrintedReceiptLine;
//import id.advancepro.com.advancepro.R;
//import id.advancepro.com.advancepro.features.sales.model.CartExtraChargeSalesModel;
//import id.advancepro.com.advancepro.features.sales.model.OrderSalesModel;
//import id.advancepro.com.advancepro.utils.CommonUtil;
//
///**
// * Created by dennis on 2/14/16.
// */
//public class PoyntUtil {
//    private static final String apiEndpoint = "https://services.poynt.net";
//
//    public static X509Certificate getPoyntCert() {
//        try {
////            System.setProperty("javax.net.debug","all");
//            URL destinationURL = new URL(apiEndpoint);
//            HttpsURLConnection conn = (HttpsURLConnection) destinationURL.openConnection();
//            conn.setSSLSocketFactory(new TLSSocketFactory());
//            conn.connect();
//            Certificate[] certs = conn.getServerCertificates();
//            if (certs.length > 0) {
//                System.out.println("");
//                System.out.println("");
//                System.out.println("");
//                System.out.println("################################################################");
//                System.out.println("");
//                System.out.println("");
//                System.out.println("");
//                Certificate cert = certs[0];
//                System.out.println("Certificate is: " + cert);
//                if (cert instanceof X509Certificate) {
//                    try {
//                        ((X509Certificate) cert).checkValidity();
//                        System.out.println("Certificate is active for current date");
//                        return (X509Certificate) cert;
//                    } catch (CertificateExpiredException cee) {
//                        System.out.println("Certificate is expired");
//                    } catch (CertificateNotYetValidException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                } else {
//                    System.err.println("Unknown certificate type: " + cert);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Order generateOrder(HashMap<Integer, OrderSalesModel> salesOrderList, CartExtraChargeSalesModel extraChargeSalesModel, int refNumber) {
//        Order order = new Order();
//        order.setId(UUID.randomUUID());
//        order.setOrderNumber(Integer.toString(refNumber));
//        List<OrderItem> items = new ArrayList<OrderItem>();
//        // create some dummy items to display in second screen
//        items = new ArrayList<OrderItem>();
//
//        long subTotal = 0;
//
//        for (HashMap.Entry<Integer, OrderSalesModel> entry : salesOrderList.entrySet()) {
//            OrderSalesModel orderSalesModel = entry.getValue();
//            OrderItem item = new OrderItem();
//
//            item.setName(orderSalesModel.getProductName());
//            item.setUnitPrice(CommonUtil.dollarsToCents(orderSalesModel.getPriceOriginal()));
//            item.setDiscount(CommonUtil.dollarsToCents(orderSalesModel.getDiscountAmount()));
//            item.setQuantity( orderSalesModel.getQuantity());
//            item.setUnitOfMeasure(UnitOfMeasure.EACH);
//            item.setStatus(OrderItemStatus.FULFILLED);
//            item.setTax(CommonUtil.dollarsToCents(orderSalesModel.getTax()));
//            items.add(item);
//
//            subTotal += new BigDecimal(orderSalesModel.getProductTotalPrice()).setScale(0, RoundingMode.HALF_UP).longValue();
//        }
//
//        /*for (OrderItem item : items) {
//            BigDecimal itemRoundedPrice = new BigDecimal(item.getUnitPrice() * item.getQuantity());
//            itemRoundedPrice.setScale(0, RoundingMode.HALF_UP);
//            subTotal += itemRoundedPrice.longValue();
//        }
//*/
//        ArrayList<Fee> fees = new ArrayList<>();
//
//        if (extraChargeSalesModel.getShipping() != 0) {
//            Fee shippingFee = new Fee();
//            shippingFee.setAmount(CommonUtil.dollarsToCents(extraChargeSalesModel.getShipping()));
//            shippingFee.setName("Shipping");
//            //shippingFee.setAppliedBeforeTax(true);
//
//            fees.add(shippingFee);
//        }
//
//        if (extraChargeSalesModel.getAdjustment() != 0) {
//            Fee adjustmentFee = new Fee();
//            adjustmentFee.setAmount(CommonUtil.dollarsToCents(extraChargeSalesModel.getAdjustment()));
//            adjustmentFee.setName("Adjustment");
//            fees.add(adjustmentFee);
//        }
//
//        order.setFees(fees);
//
//        OrderAmounts amounts = new OrderAmounts();
//        amounts.setCurrency("USD");
//        amounts.setSubTotal(subTotal);
//        amounts.setNetTotal(subTotal);
//
//        long discAmount = 0;
//        for (OrderItem item : items) {
//            discAmount -= item.getDiscount();
//        }
//
//        amounts.setDiscountTotal(discAmount);
//
//        amounts.setTaxTotal(CommonUtil.dollarsToCents(extraChargeSalesModel.getTotalTax()));
//
//        long feeAmount = 0;
//
//        for (OrderItem item : items) {
//            if (item.getFee() != null) {
//                feeAmount += item.getFee();
//            }
//        }
//
//        List<Fee> totalFees = CommonUtil.safeList(order.getFees());
//        for (Fee fee : totalFees) {
//            if (fee.getAmount() != null) {
//                feeAmount += fee.getAmount();
//            }
//        }
//        amounts.setFeeTotal(feeAmount);
//
//        // for simplicity assuming netTotal is the same as subTotal
//        // normally: netTotal = subTotal + taxTotal - discountTotal + cashback
//        amounts.setNetTotal(subTotal + amounts.getTaxTotal() - amounts.getDiscountTotal());
//        order.setAmounts(amounts);
//
//        OrderStatuses orderStatuses = new OrderStatuses();
//        orderStatuses.setStatus(OrderStatus.COMPLETED);
//        order.setStatuses(orderStatuses);
//        order.setId(UUID.randomUUID());
//
//        order.setItems(items);
//
//        return order;
//    }
//
//    public static Product createProduct() {
//        String sku = "1234567890";
//        /*
//
//    "selectableVariants": [
//        {
//            "selectableVariations": [
//                {
//                    "values": [
//                        {
//                            "defaultValue": true,
//                            "priceDelta": {
//                                "amount": 0,
//                                "currency": "USD"
//                            },
//                            "name": "M"
//                        },
//                        {
//                            "priceDelta": {
//                                "amount": 0,
//                                "currency": "USD"
//                            },
//                            "name": "L"
//                        },
//                        {
//                            "priceDelta": {
//                                "amount": 0,
//                                "currency": "USD"
//                            },
//                            "name": "XL"
//                        }
//                    ],
//                    "attribute": "Size",
//                    "cardinality": "1"
//                },
//
//
//         */
//        Product product = new Product();
//        product.setName("Nike Two");
//        CurrencyAmount price = new CurrencyAmount(19900l, "USD");
//        product.setPrice(price);
//        product.setSku(sku);
//        product.setType(ProductType.SIMPLE);
//        Variant variant = new Variant();
//        variant.setSku(sku);
//
//        // size options (only 1 allowed)
//        SelectableVariation sizeVariation = new SelectableVariation();
//        sizeVariation.setAttribute("Size");
//        sizeVariation.setCardinality("1");
//
//        SelectableValue valueM = new SelectableValue();
//        valueM.setDefaultValue(true);
//        valueM.setName("M");
//        valueM.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        SelectableValue valueL = new SelectableValue();
//        valueL.setName("L");
//        valueL.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        SelectableValue valueXL = new SelectableValue();
//        valueXL.setName("XL");
//        valueXL.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        sizeVariation.setValues(Arrays.asList(valueM, valueL, valueXL));
//
//        // color options (only 1 allowed)
//        SelectableVariation colorVariation = new SelectableVariation();
//        colorVariation.setAttribute("Color");
//        colorVariation.setCardinality("1");
//
//        SelectableValue colorWhite = new SelectableValue();
//        colorWhite.setDefaultValue(true);
//        colorWhite.setName("White");
//        colorWhite.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        SelectableValue colorGreen = new SelectableValue();
//        colorGreen.setName("Green");
//        colorGreen.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        SelectableValue colorBlue = new SelectableValue();
//        colorBlue.setName("Blue");
//        colorBlue.setPriceDelta(new CurrencyAmount(0l, "USD"));
//
//        colorVariation.setValues(Arrays.asList(colorWhite, colorGreen, colorBlue));
//
//
//        variant.setSelectableVariations(Arrays.asList(sizeVariation, colorVariation));
//        product.setSelectableVariants(Collections.singletonList(variant));
//
//        return product;
//    }
//
//    public static PrintedReceipt generateReceipt(Resources resources) {
//        PrintedReceipt printedReceipt = new PrintedReceipt();
//
//        // BODY
//        List<PrintedReceiptLine> body = new ArrayList<PrintedReceiptLine>();
//
//
//        body.add(newLine(" Check-in REWARD  "));
//        body.add(newLine(""));
//        body.add(newLine("FREE Reg. 1/2 Order"));
//        body.add(newLine("Nachos or CHEESE"));
//        body.add(newLine("Quesadilla with min."));
//        body.add(newLine("$ 15 bill."));
//        body.add(newLine(".................."));
//        body.add(newLine("John Doe"));
//        body.add(newLine("BD: May-5, AN: Aug-4"));
//        body.add(newLine("john.doe@gmail.com"));
//        body.add(newLine("Visit #23"));
//        body.add(newLine("Member since: 15 June 2013"));
//        body.add(newLine(".................."));
//        body.add(newLine("Apr-5-2013 3:25 PM"));
//        body.add(newLine("Casa Orozco, Dublin, CA"));
//        body.add(newLine(".................."));
//        body.add(newLine("Coupon#: 1234-5678"));
//        body.add(newLine(" Check-in REWARD  "));
//        body.add(newLine(""));
//        body.add(newLine("FREE Reg. 1/2 Order"));
//        body.add(newLine("Nachos or CHEESE"));
//        body.add(newLine("Quesadilla with min."));
//        body.add(newLine("$ 15 bill."));
//        body.add(newLine(".................."));
//        body.add(newLine("John Doe"));
//        body.add(newLine("BD: May-5, AN: Aug-4"));
//        body.add(newLine("john.doe@gmail.com"));
//        body.add(newLine("Visit #23"));
//        body.add(newLine("Member since: 15 June 2013"));
//        body.add(newLine(".................."));
//        body.add(newLine("Apr-5-2013 3:25 PM"));
//        body.add(newLine("Casa Orozco, Dublin, CA"));
//        body.add(newLine(".................."));
//        body.add(newLine("Coupon#: 1234-5678"));
//        body.add(newLine("  Powered by Poynt"));
//        printedReceipt.setBody(body);
//
//        // to print image
//        printedReceipt.setHeaderImage(BitmapFactory.decodeResource(resources, R.drawable.logo));
//        printedReceipt.setFooterImage(BitmapFactory.decodeResource(resources, R.drawable.logo));
//
//        return printedReceipt;
//    }
//
//    private static PrintedReceiptLine newLine(String s) {
//        PrintedReceiptLine line = new PrintedReceiptLine();
//        line.setText(s);
//        return line;
//    }
//
//    public static Transaction generateTransaction(Long orderAmount) {
//        Transaction transaction = new Transaction();
//
//        transaction.setId(UUID.randomUUID());
//
//        TransactionAmounts amounts = new TransactionAmounts();
//        amounts.setCurrency(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
//        amounts.setOrderAmount(orderAmount);
//        amounts.setTransactionAmount(orderAmount);
//        transaction.setAmounts(amounts);
//
//        transaction.setCreatedAt(Calendar.getInstance());
//
//        FundingSource fundingSource = new FundingSource();
//        fundingSource.setType(FundingSourceType.CREDIT_DEBIT);
//
//        Card card = new Card();
//        card.setNumberFirst6("456789");
//        card.setCardHolderFirstName("Daniel");
//        card.setCardHolderLastName("Mark");
//        card.setCurrency(Currency.getInstance(Locale.getDefault()).getCurrencyCode());
//        fundingSource.setCard(card);
//
//        transaction.setFundingSource(fundingSource);
//
//        return transaction;
//    }
//
//}
