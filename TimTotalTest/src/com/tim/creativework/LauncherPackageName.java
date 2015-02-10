
package com.tim.creativework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.tim.totaltest.R;//

import android.os.Bundle;//
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;//
import android.widget.EditText;
import android.widget.TextView;//
import android.app.Activity;//
import android.app.AlertDialog;

public class LauncherPackageName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_package_name);

        TextView showText = (TextView) findViewById(R.id.showtext);

        List<String> packageNames = new ArrayList<String>();
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        // final ResolveInfo res =
        // context.getPackageManager().resolveActivity(intent, 0);
        List<ResolveInfo> resolveInfo = this.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        // bytim:应该是加了String
        // android.content.Intent.CATEGORY_DEFAULT属性的都能加到list里面来，已测过。
        for (ResolveInfo ri : resolveInfo) {
            packageNames.add(ri.activityInfo.packageName);
            Log.e("LauncherPackageName", "packageName =" + ri.activityInfo.packageName);
        }
        if (packageNames == null || packageNames.size() == 0) {
            showText.setText("没有launcher");// return null;
        } else {
            showText.append("获取launchers名字：\n" + packageNames + "\n\n");
            Log.e("LauncherPackageName", "" + packageNames);
        }

        showText.append("SD卡总容量:" + formatSize(getSDCardMemory()[0]) + "\nSD卡可用容量:"
                + formatSize(getSDCardMemory()[1]) + "\nROM卡总容量:" + formatSize(getRomMemroy()[0])
                + "\nROM卡可用容量:" + formatSize(getRomMemroy()[1]));
        
        final EditText editor = new EditText(this);
        new AlertDialog.Builder(this)
        .setView(editor)
        .setPositiveButton("yes", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pin = editor.getText().toString();
            }
        })
        .setNegativeButton("no", null).show();
    }
    

    public long[] getSDCardMemory() {
        long[] sdCardInfo = new long[2];
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long bSize = sf.getBlockSize();
            long bCount = sf.getBlockCount();
            long availBlocks = sf.getAvailableBlocks();

            sdCardInfo[0] = bSize * bCount;// 总大小
            sdCardInfo[1] = bSize * availBlocks;// 可用大小
        }
        return sdCardInfo;
    }

    public long[] getRomMemroy() {
        long[] romInfo = new long[2];
        // Total rom memory
        romInfo[0] = getTotalInternalMemorySize();

        // Available rom memory
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        romInfo[1] = blockSize * availableBlocks;
        return romInfo;
    }

    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public String formatSize(long size) {
        String suffix = null;
        float fSize = 0;

        if (size >= 1024) {
            suffix = "KB";
            fSize = size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
//            if (fSize >= 1024) {
//                suffix = "GB";
//                fSize /= 1024;
//            }
        } else {
            fSize = size;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
}
