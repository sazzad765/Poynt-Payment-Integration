package com.example.native_code_test.java_class;

import android.app.ProgressDialog;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Handler;

import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtil {
    private static ProgressDialog mProgressDialog;
    private static CommonUtil commonUtil;
    private static Handler handler = new Handler();

    public static CommonUtil getInstance() {
        if (commonUtil == null) {
            return new CommonUtil();
        } else {
            return commonUtil;
        }
    }

    /**
     * Shows a progress dialog with default message
     */
//    public static void showProgressDialog(Context context) {
//        if (mProgressDialog != null  && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//        mProgressDialog = ProgressDialog.show(context, null, context.getString(R.string.please_wait));
//    }

    /**
     * Show a progress dialog with default message "please wait.."
     *
     * @param title title of progress dialog
     */
//    public static void showProgressDialog(Context context, final String title) {
//        if (mProgressDialog != null  && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//        mProgressDialog = ProgressDialog.show(context, title, context.getString(R.string.please_wait));
//    }

    /**
     * Shows a progress dialog with title and message and not cancelable
     *
     * @param context Activity reference
     * @param title   Title of the Progress dialog
     * @param message Message of the Progress dialog
     */
    public static void showProgressDialog(Context context, String title, String message) {
        if (mProgressDialog != null  && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = ProgressDialog.show(context, title, message);
    }

    /**
     * Shows a delayed progress dialog with title and message.
     * If hideProgressDialog() is called before delay period has elapsed, the dialog will not show.
     *
     * @param context Activity reference
     * @param title   Title of the Progress dialog
     * @param message Message of the Progress dialog
     * @param delay   Number of milliseconds to delay the prompt
     */
    public static void showProgressDialog(Context context, String title, String message, int delay) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> showProgressDialog(context, title, message), delay);
    }

    /**
     * Show a progress dialog after specified time (cancelled by hideProgressDialog()).
     *
     * @param title             title of progress dialog
     * @param message           message of the progress
     * @param delay             milliseconds to delay the display
     * @param onDismissCallback function to be called when user dismisses dialog
     */
    public static void showProgressDialog(Context context, final String title, final String message, int delay, DialogOnDismissCallback onDismissCallback) {
        if (mProgressDialog != null) {
            mProgressDialog.setOnDismissListener(null);
        }
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> {
            showProgressDialog(context, title, message);
            if (mProgressDialog != null) {
                mProgressDialog.setOnDismissListener(x -> onDismissCallback.execute());
            }
        }, delay);
    }

    /**
     * Hides the visible progress dialog
     */
    public static void hideProgressDialog() {
        handler.removeCallbacksAndMessages(null);

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

//    public static ProgressDialog getProgressDialog(Context context) {
//        if (mProgressDialog == null) {
//            mProgressDialog = ProgressDialog.show(context, null, context.getString(R.string.please_wait), true);
//        }
//
//        return mProgressDialog;
//    }

    public interface DialogOnDismissCallback {
        void execute();
    }

    /**
     * Shows a Alert Dialog with title and message and a OK button
     *
     * @param context Activity Reference
     * @param title   Title of the Alert Dialog
     * @param message Message of Alert Dialog
     */
//    public static AlertDialog.Builder showAlertDialog(Context context, String title, String message) {
//        AlertDialog.Builder builder;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
//        } else {
//            builder = new AlertDialog.Builder(context);
//        }
//        builder.setTitle(title)
//                .setMessage(message)
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//
//        return builder;
//    }
//
//    public static AlertDialog.Builder getAlertDialog(Context context, String title, String message) {
//        AlertDialog.Builder builder;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
//        } else {
//            builder = new AlertDialog.Builder(context);
//        }
//        builder.setTitle(title)
//                .setMessage(message);
//
//        return builder;
//
//
//    }


    /**
     * Convert a base64 to Bitmap
     *
     * @param base64EncodedImage base64 of the image
     * @return Bitmap of the image
     */
    public static Bitmap getBitmapFromBase64(byte[] base64EncodedImage) {
        return BitmapFactory.decodeByteArray(base64EncodedImage, 0, base64EncodedImage.length);
    }

    public static String getBase64FromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }


    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean validateLetters(String text) {
        String regex = "^[\\p{L} .'-]+$";
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text).find();
    }

    public static boolean validateText(String regex, String text) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text).find();
    }


    public static boolean isValidNumber(String string) {
        return string.matches("^[+-]?(?=.)\\d*(\\.\\d+)?$");
    }

    public static String formatDecimal(double aDouble) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(aDouble);
    }

    public static Double round2(Double val) {
        return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static Long dollarsToCents(Double dollars) {
        return Math.round(dollars * 100);
    }

    public static List safeList(List maybeList ) {
        return maybeList == null ? Collections.EMPTY_LIST : maybeList;
    }

    public static void showToast(Context context, String msg) {
//        StyleableToast.makeText(context, msg, Toast.LENGTH_SHORT, R.style.mytoast).show();

    }

    public static void showKeyboard(Context context, View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
