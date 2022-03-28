package com.example.native_code_test

import android.content.res.Resources
import android.graphics.BitmapFactory
import co.poynt.api.model.*
import co.poynt.os.model.PrintedReceipt
import co.poynt.os.model.PrintedReceiptLine
import com.example.native_code_test.java_class.CommonUtil
import com.example.native_code_test.java_class.TLSSocketFactory
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URL
import java.security.cert.CertificateExpiredException
import java.security.cert.CertificateNotYetValidException
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.HttpsURLConnection

class PoyntUtils {
    private val apiEndpoint = "https://services.poynt.net"

    fun getPoyntCert(): X509Certificate? {
        try {
//            System.setProperty("javax.net.debug","all");
            val destinationURL = URL(apiEndpoint)
            val conn = destinationURL.openConnection() as HttpsURLConnection
            conn.sslSocketFactory = TLSSocketFactory()
            conn.connect()
            val certs = conn.serverCertificates
            if (certs.size > 0) {
                println("")
                println("")
                println("")
                println("################################################################")
                println("")
                println("")
                println("")
                val cert = certs[0]
                println("Certificate is: $cert")
                if (cert is X509Certificate) {
                    try {
                        cert.checkValidity()
                        println("Certificate is active for current date")
                        return cert
                    } catch (cee: CertificateExpiredException) {
                        println("Certificate is expired")
                    } catch (e: CertificateNotYetValidException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                } else {
                    System.err.println("Unknown certificate type: $cert")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun generateOrder(
        salesOrderList: HashMap<Int?, Product>,
//        extraChargeSalesModel: CartExtraChargeSalesModel,
        refNumber: Int
    ): Order? {
        val order = Order()
        order.id = UUID.randomUUID()
        order.orderNumber = Integer.toString(refNumber)
        var items: MutableList<OrderItem> = ArrayList()
        // create some dummy items to display in second screen
        items = ArrayList()
        var subTotal: Long = 0
        for ((_, orderSalesModel) in salesOrderList) {
            val item = OrderItem()
            item.name = orderSalesModel.getProductName()
            item.unitPrice = CommonUtil.dollarsToCents(orderSalesModel.getUnitPrice())
            item.discount = CommonUtil.dollarsToCents(orderSalesModel.getDiscount())
            item.quantity = orderSalesModel.getQuantity()
            item.unitOfMeasure = UnitOfMeasure.EACH
            item.status = OrderItemStatus.FULFILLED
            item.tax = CommonUtil.dollarsToCents(orderSalesModel.getTax())
            items.add(item)
            subTotal += BigDecimal(orderSalesModel.getUnitPrice()).setScale(
                0,
                RoundingMode.HALF_UP
            ).toLong()
        }

        /*for (OrderItem item : items) {
            BigDecimal itemRoundedPrice = new BigDecimal(item.getUnitPrice() * item.getQuantity());
            itemRoundedPrice.setScale(0, RoundingMode.HALF_UP);
            subTotal += itemRoundedPrice.longValue();
        }
*/
//        val fees = ArrayList<Fee>()
//        if (extraChargeSalesModel.getShipping() !== 0) {
//            val shippingFee = Fee()
//            shippingFee.amount = CommonUtil.dollarsToCents(extraChargeSalesModel.getShipping())
//            shippingFee.name = "Shipping"
//            //shippingFee.setAppliedBeforeTax(true);
//            fees.add(shippingFee)
//        }
//        if (extraChargeSalesModel.getAdjustment() !== 0) {
//            val adjustmentFee = Fee()
//            adjustmentFee.amount = CommonUtil.dollarsToCents(extraChargeSalesModel.getAdjustment())
//            adjustmentFee.name = "Adjustment"
//            fees.add(adjustmentFee)
//        }
//        order.fees = fees
        val amounts = OrderAmounts()
        amounts.currency = "USD"
        amounts.subTotal = subTotal
        amounts.netTotal = subTotal
        var discAmount: Long = 0
        for (item in items) {
            discAmount -= item.discount
        }
        amounts.discountTotal = discAmount
//        amounts.taxTotal = CommonUtil.dollarsToCents(extraChargeSalesModel.getTotalTax())
        var feeAmount: Long = 0
        for (item in items) {
            if (item.fee != null) {
                feeAmount += item.fee
            }
        }
        val totalFees: List<Fee> = CommonUtil.safeList(order.fees) as List<Fee>
        for (fee in totalFees) {
            if (fee.amount != null) {
                feeAmount += fee.amount
            }
        }
        amounts.feeTotal = feeAmount

        // for simplicity assuming netTotal is the same as subTotal
        // normally: netTotal = subTotal + taxTotal - discountTotal + cashback
        amounts.netTotal = subTotal + amounts.taxTotal - amounts.discountTotal
        order.amounts = amounts
        val orderStatuses = OrderStatuses()
        orderStatuses.status = OrderStatus.COMPLETED
        order.statuses = orderStatuses
        order.id = UUID.randomUUID()
        order.items = items
        return order
    }

//    fun createProduct(): Product? {
//        val sku = "1234567890"
//
//        val product = Product()
//        product.name = "Nike Two"
//        val price = CurrencyAmount(19900L, "USD")
//        product.price = price
//        product.sku = sku
//        product.type = ProductType.SIMPLE
//        val variant = Variant()
//        variant.sku = sku
//
//        // size options (only 1 allowed)
//        val sizeVariation = SelectableVariation()
//        sizeVariation.attribute = "Size"
//        sizeVariation.cardinality = "1"
//        val valueM = SelectableValue()
//        valueM.isDefaultValue = true
//        valueM.name = "M"
//        valueM.priceDelta = CurrencyAmount(0L, "USD")
//        val valueL = SelectableValue()
//        valueL.name = "L"
//        valueL.priceDelta = CurrencyAmount(0L, "USD")
//        val valueXL = SelectableValue()
//        valueXL.name = "XL"
//        valueXL.priceDelta = CurrencyAmount(0L, "USD")
//        sizeVariation.values = Arrays.asList(valueM, valueL, valueXL)
//
//        // color options (only 1 allowed)
//        val colorVariation = SelectableVariation()
//        colorVariation.attribute = "Color"
//        colorVariation.cardinality = "1"
//        val colorWhite = SelectableValue()
//        colorWhite.isDefaultValue = true
//        colorWhite.name = "White"
//        colorWhite.priceDelta = CurrencyAmount(0L, "USD")
//        val colorGreen = SelectableValue()
//        colorGreen.name = "Green"
//        colorGreen.priceDelta = CurrencyAmount(0L, "USD")
//        val colorBlue = SelectableValue()
//        colorBlue.name = "Blue"
//        colorBlue.priceDelta = CurrencyAmount(0L, "USD")
//        colorVariation.values = Arrays.asList(colorWhite, colorGreen, colorBlue)
//        variant.selectableVariations = Arrays.asList(sizeVariation, colorVariation)
//        product.selectableVariants = listOf(variant)
//        return product
//    }

    fun generateReceipt(resources: Resources?): PrintedReceipt? {
        val printedReceipt = PrintedReceipt()

        // BODY
        val body: MutableList<PrintedReceiptLine> = ArrayList()
        body.add(newLine(" Check-in REWARD  "))
        body.add(newLine(""))
        body.add(newLine("FREE Reg. 1/2 Order"))
        body.add(newLine("Nachos or CHEESE"))
        body.add(newLine("Quesadilla with min."))
        body.add(newLine("$ 15 bill."))
        body.add(newLine(".................."))
        body.add(newLine("John Doe"))
        body.add(newLine("BD: May-5, AN: Aug-4"))
        body.add(newLine("john.doe@gmail.com"))
        body.add(newLine("Visit #23"))
        body.add(newLine("Member since: 15 June 2013"))
        body.add(newLine(".................."))
        body.add(newLine("Apr-5-2013 3:25 PM"))
        body.add(newLine("Casa Orozco, Dublin, CA"))
        body.add(newLine(".................."))
        body.add(newLine("Coupon#: 1234-5678"))
        body.add(newLine(" Check-in REWARD  "))
        body.add(newLine(""))
        body.add(newLine("FREE Reg. 1/2 Order"))
        body.add(newLine("Nachos or CHEESE"))
        body.add(newLine("Quesadilla with min."))
        body.add(newLine("$ 15 bill."))
        body.add(newLine(".................."))
        body.add(newLine("John Doe"))
        body.add(newLine("BD: May-5, AN: Aug-4"))
        body.add(newLine("john.doe@gmail.com"))
        body.add(newLine("Visit #23"))
        body.add(newLine("Member since: 15 June 2013"))
        body.add(newLine(".................."))
        body.add(newLine("Apr-5-2013 3:25 PM"))
        body.add(newLine("Casa Orozco, Dublin, CA"))
        body.add(newLine(".................."))
        body.add(newLine("Coupon#: 1234-5678"))
        body.add(newLine("  Powered by Poynt"))
        printedReceipt.body = body

        // to print image
//        printedReceipt.headerImage = BitmapFactory.decodeResource(resources, R.drawable.logo)
//        printedReceipt.footerImage = BitmapFactory.decodeResource(resources, R.drawable.logo)
        return printedReceipt
    }

    private fun newLine(s: String): PrintedReceiptLine {
        val line = PrintedReceiptLine()
        line.text = s
        return line
    }

    fun generateTransaction(orderAmount: Long?): Transaction? {
        val transaction = Transaction()
        transaction.id = UUID.randomUUID()
        val amounts = TransactionAmounts()
        amounts.currency = Currency.getInstance(Locale.getDefault()).currencyCode
        amounts.orderAmount = orderAmount
        amounts.transactionAmount = orderAmount
        transaction.amounts = amounts
        transaction.createdAt = Calendar.getInstance()
        val fundingSource = FundingSource()
        fundingSource.type = FundingSourceType.CREDIT_DEBIT
        val card = Card()
        card.numberFirst6 = "456789"
        card.cardHolderFirstName = "Daniel"
        card.cardHolderLastName = "Mark"
        card.currency = Currency.getInstance(Locale.getDefault()).currencyCode
        fundingSource.card = card
        transaction.fundingSource = fundingSource
        return transaction
    }


}