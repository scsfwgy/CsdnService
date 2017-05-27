package com.gaoyuan.utils;

import static com.gaoyuan.utils.MyConstUtils.BYTE;
import static com.gaoyuan.utils.MyConstUtils.GB;
import static com.gaoyuan.utils.MyConstUtils.KB;
import static com.gaoyuan.utils.MyConstUtils.MB;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class MyConvertUtils extends MyBaseUtils {
	private MyConvertUtils() {
	        throw new UnsupportedOperationException("u can't instantiate me...");
	    }

	    static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	    /**
	     * byteArr转hexString
	     * <p>例如：</p>
	     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
	     *
	     * @param bytes 字节数组
	     * @return 16进制大写字符串
	     */
	    public static String bytes2HexString(byte[] bytes) {
	        if (bytes == null) return null;
	        int len = bytes.length;
	        if (len <= 0) return null;
	        char[] ret = new char[len << 1];
	        for (int i = 0, j = 0; i < len; i++) {
	            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
	            ret[j++] = hexDigits[bytes[i] & 0x0f];
	        }
	        return new String(ret);
	    }

	    /**
	     * hexString转byteArr
	     * <p>例如：</p>
	     * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
	     *
	     * @param hexString 十六进制字符串
	     * @return 字节数组
	     */
	    public static byte[] hexString2Bytes(String hexString) {
		if (MyStringUtils.isSpace(hexString))
			return null;
	        int len = hexString.length();
	        if (len % 2 != 0) {
	            hexString = "0" + hexString;
	            len = len + 1;
	        }
	        char[] hexBytes = hexString.toUpperCase().toCharArray();
	        byte[] ret = new byte[len >> 1];
	        for (int i = 0; i < len; i += 2) {
	            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
	        }
	        return ret;
	    }

	    /**
	     * hexChar转int
	     *
	     * @param hexChar hex单个字节
	     * @return 0..15
	     */
	    private static int hex2Dec(char hexChar) {
	        if (hexChar >= '0' && hexChar <= '9') {
	            return hexChar - '0';
	        } else if (hexChar >= 'A' && hexChar <= 'F') {
	            return hexChar - 'A' + 10;
	        } else {
	            throw new IllegalArgumentException();
	        }
	    }

	    /**
	     * charArr转byteArr
	     *
	     * @param chars 字符数组
	     * @return 字节数组
	     */
	    public static byte[] chars2Bytes(char[] chars) {
	        if (chars == null || chars.length <= 0) return null;
	        int len = chars.length;
	        byte[] bytes = new byte[len];
	        for (int i = 0; i < len; i++) {
	            bytes[i] = (byte) (chars[i]);
	        }
	        return bytes;
	    }

	    /**
	     * byteArr转charArr
	     *
	     * @param bytes 字节数组
	     * @return 字符数组
	     */
	    public static char[] bytes2Chars(byte[] bytes) {
	        if (bytes == null) return null;
	        int len = bytes.length;
	        if (len <= 0) return null;
	        char[] chars = new char[len];
	        for (int i = 0; i < len; i++) {
	            chars[i] = (char) (bytes[i] & 0xff);
	        }
	        return chars;
	    }

	    /**
	     * 字节数转以unit为单位的size
	     *
	     * @param byteNum 字节数
	     * @param unit    单位类型
	     *                <ul>
	     *                <li>{@link MyConstUtils.MemoryUnit#BYTE}: 字节</li>
	     *                <li>{@link MyConstUtils.MemoryUnit#KB}  : 千字节</li>
	     *                <li>{@link MyConstUtils.MemoryUnit#MB}  : 兆</li>
	     *                <li>{@link MyConstUtils.MemoryUnit#GB}  : GB</li>
	     *                </ul>
	     * @return 以unit为单位的size
	     */
	    public static double byte2Size(long byteNum, MyConstUtils.MemoryUnit unit) {
	        if (byteNum < 0) return -1;
	        switch (unit) {
	            default:
	            case BYTE:
	                return (double) byteNum / BYTE;
	            case KB:
	                return (double) byteNum / KB;
	            case MB:
	                return (double) byteNum / MB;
	            case GB:
	                return (double) byteNum / GB;
	        }
	    }

	    /**
	     * 以unit为单位的size转字节数
	     *
	     * @param size 大小
	     * @param unit 单位类型
	     *             <ul>
	     *             <li>{@link MyConstUtils.MemoryUnit#BYTE}: 字节</li>
	     *             <li>{@link MyConstUtils.MemoryUnit#KB}  : 千字节</li>
	     *             <li>{@link MyConstUtils.MemoryUnit#MB}  : 兆</li>
	     *             <li>{@link MyConstUtils.MemoryUnit#GB}  : GB</li>
	     *             </ul>
	     * @return 字节数
	     */
	    public static long size2Byte(long size, MyConstUtils.MemoryUnit unit) {
	        if (size < 0) return -1;
	        switch (unit) {
	            default:
	            case BYTE:
	                return size * BYTE;
	            case KB:
	                return size * KB;
	            case MB:
	                return size * MB;
	            case GB:
	                return size * GB;
	        }
	    }

	    /**
	     * 字节数转合适大小
	     * <p>保留3位小数</p>
	     *
	     * @param byteNum 字节数
	     * @return 1...1024 unit
	     */
	    public static String byte2FitSize(long byteNum) {
	        if (byteNum < 0) {
	            return "shouldn't be less than zero!";
	        } else if (byteNum < KB) {
	            return String.format(Locale.getDefault(), "%.3fB", (double) byteNum);
	        } else if (byteNum < MB) {
	            return String.format(Locale.getDefault(), "%.3fKB", (double) byteNum / KB);
	        } else if (byteNum < GB) {
	            return String.format(Locale.getDefault(), "%.3fMB", (double) byteNum / MB);
	        } else {
	            return String.format(Locale.getDefault(), "%.3fGB", (double) byteNum / GB);
	        }
	    }

	    /**
	     * bytes转bits
	     *
	     * @param bytes 字节数组
	     * @return bits
	     */
	    public static String bytes2Bits(byte[] bytes) {
	        StringBuilder sb = new StringBuilder();
	        for (byte aByte : bytes) {
	            for (int j = 7; j >= 0; --j) {
	                sb.append(((aByte >> j) & 0x01) == 0 ? '0' : '1');
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * bits转bytes
	     *
	     * @param bits 二进制
	     * @return bytes
	     */
	    public static byte[] bits2Bytes(String bits) {
	        int lenMod = bits.length() % 8;
	        int byteLen = bits.length() / 8;
	        // 不是8的倍数前面补0
	        if (lenMod != 0) {
	            for (int i = lenMod; i < 8; i++) {
	                bits = "0" + bits;
	            }
	            byteLen++;
	        }
	        byte[] bytes = new byte[byteLen];
	        for (int i = 0; i < byteLen; ++i) {
	            for (int j = 0; j < 8; ++j) {
	                bytes[i] <<= 1;
	                bytes[i] |= bits.charAt(i * 8 + j) - '0';
	            }
	        }
	        return bytes;
	    }

	    /**
	     * inputStream转outputStream
	     *
	     * @param is 输入流
	     * @return outputStream子类
	     */
	    public static ByteArrayOutputStream input2OutputStream(InputStream is) {
	        if (is == null) return null;
	        try {
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            byte[] b = new byte[KB];
	            int len;
	            while ((len = is.read(b, 0, KB)) != -1) {
	                os.write(b, 0, len);
	            }
	            return os;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
			MyCloseUtils.closeIO(is);
	        }
	    }

	    /**
	     * outputStream转inputStream
	     *
	     * @param out 输出流
	     * @return inputStream子类
	     */
	    public ByteArrayInputStream output2InputStream(OutputStream out) {
	        if (out == null) return null;
	        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
	    }

	    /**
	     * inputStream转byteArr
	     *
	     * @param is 输入流
	     * @return 字节数组
	     */
	    public static byte[] inputStream2Bytes(InputStream is) {
	        if (is == null) return null;
	        return input2OutputStream(is).toByteArray();
	    }

	    /**
	     * byteArr转inputStream
	     *
	     * @param bytes 字节数组
	     * @return 输入流
	     */
	    public static InputStream bytes2InputStream(byte[] bytes) {
	        if (bytes == null || bytes.length <= 0) return null;
	        return new ByteArrayInputStream(bytes);
	    }

	    /**
	     * outputStream转byteArr
	     *
	     * @param out 输出流
	     * @return 字节数组
	     */
	    public static byte[] outputStream2Bytes(OutputStream out) {
	        if (out == null) return null;
	        return ((ByteArrayOutputStream) out).toByteArray();
	    }

	    /**
	     * outputStream转byteArr
	     *
	     * @param bytes 字节数组
	     * @return 字节数组
	     */
	    public static OutputStream bytes2OutputStream(byte[] bytes) {
	        if (bytes == null || bytes.length <= 0) return null;
	        ByteArrayOutputStream os = null;
	        try {
	            os = new ByteArrayOutputStream();
	            os.write(bytes);
	            return os;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        } finally {
			MyCloseUtils.closeIO(os);
	        }
	    }

	    /**
	     * inputStream转string按编码
	     *
	     * @param is          输入流
	     * @param charsetName 编码格式
	     * @return 字符串
	     */
	    public static String inputStream2String(InputStream is, String charsetName) {
		if (is == null || MyStringUtils.isSpace(charsetName))
			return null;
	        try {
	            return new String(inputStream2Bytes(is), charsetName);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * string转inputStream按编码
	     *
	     * @param string      字符串
	     * @param charsetName 编码格式
	     * @return 输入流
	     */
	    public static InputStream string2InputStream(String string, String charsetName) {
		if (string == null || MyStringUtils.isSpace(charsetName))
			return null;
	        try {
	            return new ByteArrayInputStream(string.getBytes(charsetName));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * outputStream转string按编码
	     *
	     * @param out         输出流
	     * @param charsetName 编码格式
	     * @return 字符串
	     */
	    public static String outputStream2String(OutputStream out, String charsetName) {
		if (out == null || MyStringUtils.isSpace(charsetName))
			return null;
	        try {
	            return new String(outputStream2Bytes(out), charsetName);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * string转outputStream按编码
	     *
	     * @param string      字符串
	     * @param charsetName 编码格式
	     * @return 输入流
	     */
	    public static OutputStream string2OutputStream(String string, String charsetName) {
		if (string == null || MyStringUtils.isSpace(charsetName))
			return null;
	        try {
	            return bytes2OutputStream(string.getBytes(charsetName));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


}
