package pt.lsts.accl.util;

import pt.lsts.accl.ACCL;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by jloureiro on 1/8/15.
 */
public class AndroidUtil {


    public static void showToastShort(FragmentActivity fragmentActivity, final String msg){
        fragmentActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ACCL.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToastLong(FragmentActivity fragmentActivity, final String msg){
        fragmentActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ACCL.getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void loadFragment(FragmentActivity fragmentActivity, Fragment fragment, int fragmentContainerID){
        fragmentActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .add(fragmentContainerID,fragment).commit();
        fragmentActivity.getSupportFragmentManager().executePendingTransactions();
    }

    public static void removeFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment).commit();
        fragmentActivity.getSupportFragmentManager().executePendingTransactions();
    }

    public static void removeAllFragments(FragmentActivity fragmentActivity){
        for ( Fragment fragment : fragmentActivity.getSupportFragmentManager().getFragments()){
            removeFragment(fragmentActivity, fragment);
        }
    }

}