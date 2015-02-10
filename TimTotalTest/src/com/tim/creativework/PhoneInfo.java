
package com.tim.creativework;

import java.io.File;
import java.lang.Character.UnicodeBlock;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;

import com.tim.totaltest.R;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract.FullNameStyle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class PhoneInfo extends Activity {

    TextView showtext = null;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_info);
        // bytim:关于手机的一些信息
        String infoTitle = "<font color=\"#ff0000\">手机信息：</font><br>";
        String RAMTitle = "<font color=\"#ff0000\">RAM状况：</font><br>";
        String SDTitle = "<font color=\"#ff0000\">SD卡状况：</font><br>";
        String ROMTitle = "<font color=\"#ff0000\">ROM状况：</font><br>";
//Log.d("tim-lc", "country:"+Locale.getDefault().getCountry()+" lang:"+Locale.getDefault().getLanguage());
////        String languageToLoad  = "de";
//        Locale locale = new Locale("ru", "RU");
////        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        this.getResources().
//        getBaseContext().getResources().updateConfiguration(config, null);

        StringBuilder phoneInfo = new StringBuilder();
        phoneInfo.append("Product: " + android.os.Build.PRODUCT
                + System.getProperty("line.separator"));
        phoneInfo.append("CPU_ABI: " + android.os.Build.CPU_ABI
                + System.getProperty("line.separator"));
        phoneInfo.append("TAGS: " + android.os.Build.TAGS + System.getProperty("line.separator"));
        phoneInfo.append("VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE
                + System.getProperty("line.separator"));
        phoneInfo.append("MODEL: " + android.os.Build.MODEL + System.getProperty("line.separator"));
        phoneInfo.append("SDK: " + android.os.Build.VERSION.SDK
                + System.getProperty("line.separator"));
        phoneInfo.append("VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE
                + System.getProperty("line.separator"));
        phoneInfo.append("DEVICE: " + android.os.Build.DEVICE
                + System.getProperty("line.separator"));
        phoneInfo.append("DISPLAY: " + android.os.Build.DISPLAY
                + System.getProperty("line.separator"));
        phoneInfo.append("BRAND: " + android.os.Build.BRAND + System.getProperty("line.separator"));
        phoneInfo.append("BOARD: " + android.os.Build.BOARD + System.getProperty("line.separator"));
        phoneInfo.append("FINGERPRINT: " + android.os.Build.FINGERPRINT
                + System.getProperty("line.separator"));
        phoneInfo.append("ID: " + android.os.Build.ID + System.getProperty("line.separator"));
        phoneInfo.append("MANUFACTURER: " + android.os.Build.MANUFACTURER
                + System.getProperty("line.separator"));
        phoneInfo.append("USER: " + android.os.Build.USER + System.getProperty("line.separator"));

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        phoneInfo.append("DeviceId(IMEI) = " + tm.getDeviceId()
                + System.getProperty("line.separator"));
        phoneInfo.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion()
                + System.getProperty("line.separator"));
        phoneInfo.append("Line1Number = " + tm.getLine1Number()
                + System.getProperty("line.separator"));
        phoneInfo.append("NetworkCountryIso = " + tm.getNetworkCountryIso()
                + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperator = " + tm.getNetworkOperator()
                + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperatorName = " + tm.getNetworkOperatorName()
                + System.getProperty("line.separator"));
        phoneInfo.append("NetworkType = " + tm.getNetworkType()
                + System.getProperty("line.separator"));
        phoneInfo.append("PhoneType = " + tm.getPhoneType() + System.getProperty("line.separator"));
        phoneInfo.append("SimCountryIso = " + tm.getSimCountryIso()
                + System.getProperty("line.separator"));
        phoneInfo.append("SimOperator = " + tm.getSimOperator()
                + System.getProperty("line.separator"));
        phoneInfo.append("SimOperatorName = " + tm.getSimOperatorName()
                + System.getProperty("line.separator"));
        phoneInfo.append("SimSerialNumber = " + tm.getSimSerialNumber()
                + System.getProperty("line.separator"));
        phoneInfo.append("SimState = " + tm.getSimState() + System.getProperty("line.separator"));
        phoneInfo.append("SubscriberId(IMSI) = " + tm.getSubscriberId()
                + System.getProperty("line.separator"));
        phoneInfo.append("VoiceMailNumber = " + tm.getVoiceMailNumber()
                + System.getProperty("line.separator"));

        showtext = (EditText) findViewById(R.id.showtext);
        showtext.setText(Html.fromHtml(infoTitle));// bytim:把\n都转换了掉了
        showtext.append(phoneInfo);
        // bytim:通过反射机制来获取手机的内存的一些信息
        Method _readProclines = null;
        try {
            Class<?> procClass;
            procClass = Class.forName("android.os.Process");
            Class<?> parameterTypes[] = new Class[] {
                    String.class, String[].class, long[].class
            };
            _readProclines = procClass.getMethod("readProcLines", parameterTypes);
            Object arglist[] = new Object[3];
            // bytim:简单来说，buffer是即将要被写入磁盘的，而cache是被从磁盘中读出来的,两者都是RAM中的数据。
            // bytim:mMemInfoFields的字段名是特定的，不能更改。
            final String[] mMemInfoFields = new String[] {
                    "MemTotal:", "MemFree:", "Buffers:", "Cached:"
            };
            long[] mMemInfoSizes = new long[mMemInfoFields.length];
            mMemInfoSizes[0] = 30;
            mMemInfoSizes[1] = -30;
            arglist[0] = new String("/proc/meminfo");
            arglist[1] = mMemInfoFields;
            arglist[2] = mMemInfoSizes;
            if (_readProclines != null) {
                _readProclines.invoke(null, arglist);
                showtext.append("\n");
                showtext.append(Html.fromHtml(RAMTitle));
                for (int i = 0; i < mMemInfoSizes.length; i++) {
                    showtext.append(mMemInfoFields[i] + mMemInfoSizes[i] / 1024+"\n");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        showtext.append("\n");
        showtext.append(Html.fromHtml(SDTitle));
        showtext.append("总容量:" + formatSize(getSDCardMemory()[0]) + "\n可用容量:"
                + formatSize(getSDCardMemory()[1]));
        showtext.append("\n\n");
        showtext.append(Html.fromHtml(ROMTitle));
        showtext.append("总容量:" + formatSize(getRomMemroy()[0]) + "\n可用容量:"
                + formatSize(getRomMemroy()[1]));
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
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
    
 /*   
    
    public static int guessFullNameStyle(String name) {
        if (name == null) {
            return FullNameStyle.UNDEFINED;
        }

        int nameStyle = FullNameStyle.UNDEFINED;
        int length = name.length();
        int offset = 0;
        while (offset < length) {
            int codePoint = Character.codePointAt(name, offset);
            if (Character.isLetter(codePoint)) {
                UnicodeBlock unicodeBlock = UnicodeBlock.of(codePoint);

                if (!isLatinUnicodeBlock(unicodeBlock)) {

                    if (isCJKUnicodeBlock(unicodeBlock)) {
                        // We don't know if this is Chinese, Japanese or Korean -
                        // trying to figure out by looking at other characters in the name
                        return guessCJKNameStyle(name, offset + Character.charCount(codePoint));
                    }

                    if (isJapanesePhoneticUnicodeBlock(unicodeBlock)) {
                        return FullNameStyle.JAPANESE;
                    }

                    if (isKoreanUnicodeBlock(unicodeBlock)) {
                        return FullNameStyle.KOREAN;
                    }
                }
                nameStyle = FullNameStyle.WESTERN;
            }
            offset += Character.charCount(codePoint);
        }
        return nameStyle;
    }
    
    private static int guessCJKNameStyle(String name, int offset) {
        int length = name.length();
        while (offset < length) {
            int codePoint = Character.codePointAt(name, offset);
            if (Character.isLetter(codePoint)) {
                UnicodeBlock unicodeBlock = UnicodeBlock.of(codePoint);
                if (isJapanesePhoneticUnicodeBlock(unicodeBlock)) {
                    return FullNameStyle.JAPANESE;
                }
                if (isKoreanUnicodeBlock(unicodeBlock)) {
                    return FullNameStyle.KOREAN;
                }
            }
            offset += Character.charCount(codePoint);
        }

        return FullNameStyle.CJK;
    }
    
    /**
     * If the supplied name style is undefined, returns a default based on the
     * language, otherwise returns the supplied name style itself.
     * 
     * @param nameStyle See {@link FullNameStyle}.
     */
  /*  public static int getAdjustedFullNameStyle(int nameStyle) {
        String mLanguage = Locale.getDefault().getLanguage().toLowerCase();
        if (nameStyle == FullNameStyle.UNDEFINED) {
            if (JAPANESE_LANGUAGE.equals(mLanguage)) {
                return FullNameStyle.JAPANESE;
            } else if (KOREAN_LANGUAGE.equals(mLanguage)) {
                return FullNameStyle.KOREAN;
            } else if (CHINESE_LANGUAGE.equals(mLanguage)) {
                return FullNameStyle.CHINESE;
            } else {
                return FullNameStyle.WESTERN;
            }
        } else if (nameStyle == FullNameStyle.CJK) {
            if (JAPANESE_LANGUAGE.equals(mLanguage)) {
                return FullNameStyle.JAPANESE;
            } else if (KOREAN_LANGUAGE.equals(mLanguage)) {
                return FullNameStyle.KOREAN;
            } else {
                return FullNameStyle.CHINESE;
            }
        }
        return nameStyle;
    }*/

}
