package com.hyn.baselibrary.avc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class DateUtil {
	
	private final static String hhmm = "HH:mm";
	private final String mmss = "mm:ss";
	private final static String mmdd = "M月d日";
	private final static String yymmdd = "yyyy年M月d日";
	
	/**
	 * 判断日期是否为今天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(String date) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		Date today = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date compDate;
		try {
			SimpleDateFormat format1 = new SimpleDateFormat(
					"yyyy-MM-dd E HH:mm:ss");
			compDate = format1.parse(date);
			return format.format(today).equals(format.format(compDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 返回格式为 xx月xx日 xx：xx
	 * 
	 * @param date
	 * @return
	 */
	public static String formatMDHM(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
		return format.format(date);
	}

	public static String formatMDHM1(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");
		String dateStr = format.format(date);
		String[] buffer = dateStr.split("月");
		int month = Integer.valueOf(buffer[0]);
		return month + "月" + buffer[1];
	}

	/**
	 * 传入字符串返回Date
	 * 
	 * @param dateStr
	 *            字符串yyyy-MM-dd HH:mm:ss格式
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String dateStr) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(dateStr);
	}

	/**
	 * 返回格式为 xx月xx日
	 * 
	 * @param date
	 * @return
	 */
	public static String formatMD(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
		return format.format(date);
	}

	/**
	 * 时差转换 00:00:00格式
	 * 
	 * @param duration
	 *            毫秒级时差
	 * @return
	 */
	public static String formatDuration(int duration) {
		int compareMinite = 0;
		int compareSec = 0;
		int compareHour = (int) (duration / 1000 / 60 / 60);
		if (compareHour == 0)// 小于一个小时
		{
			compareMinite = (int) (duration / 1000 / 60);
			if (compareMinite == 0)// 小于一分钟
			{
				compareSec = (int) (duration / 1000);
			} else // 大于一分钟小于一个小时
			{
				compareSec = (int) ((duration - (compareMinite * 1000 * 60)) / 1000);
			}
		} else// 大于一个小时
		{
			compareMinite = (int) ((duration - (compareHour * 1000 * 60 * 60)) / 1000 / 60);

			if (compareMinite == 0)// 大于一个小时小于1分钟
			{
				compareSec = (int) ((duration - (compareHour * 1000 * 60 * 60)) / 1000);
			} else// 大于一个小时大于一分钟
			{
				compareSec = (int) ((duration - (compareMinite * 1000 * 60) - (compareHour * 1000 * 60 * 60)) / 1000);
			}
		}

		String sTimeLong = "";
		if (compareHour >= 0 && compareHour < 10) {
			sTimeLong += "0" + compareHour + ":";
		} else {
			sTimeLong += compareHour + ":";
		}
		if (compareMinite >= 0 && compareMinite < 10) {
			sTimeLong += "0" + compareMinite + ":";
		} else {
			sTimeLong += compareMinite + ":";
		}
		if (compareSec >= 0 && compareSec < 10) {
			sTimeLong += "0" + compareSec;
		} else {
			sTimeLong += compareSec;
		}
		return sTimeLong;
	}

	/**
	 * 规则： 格式：yyyy年M月d日 HH:mm 当天显示时分(HH:mm)； 跨天/跨月显示月日时分 (M月d日
	 * HH:mm)；跨年显示年月日时分(yyyy年M月d日 )
	 * 
	 * @return
	 */
	public static String getFormatDate(Date targetdate) {
		SimpleDateFormat f_target = new SimpleDateFormat("yyyy-M-d HH:mm");
		String targetDateTimeStr = f_target.format(targetdate);
		Date nowDate = new Date();
		// 当天显示时分 去掉“YYYY年M月D日 ”
		targetDateTimeStr = "今天  " + targetDateTimeStr.replace(new SimpleDateFormat(
				"yyyy-M-d ").format(nowDate), "");
		// 跨天显示月日时分;跨月显示月日时分 去掉（YYYY年）
		targetDateTimeStr = targetDateTimeStr.replace("今天  " + new SimpleDateFormat(
				"yyyy-").format(nowDate), "");
		// 跨年去掉时间（去掉 HH:mm）
		if (targetDateTimeStr.length() >= 18) {
			targetDateTimeStr = targetDateTimeStr.replace(new SimpleDateFormat(
					" HH:mm").format(targetdate), "");
			targetDateTimeStr = targetDateTimeStr.replace("今天  ", "");
		}

		return targetDateTimeStr;
	}

	/**
	 * 规则： 格式：yyyy年M月d日 HH:mm 当天显示时分(HH:mm)； 跨天/跨月显示月日时分 (M月d日
	 * HH:mm)；跨年显示年月日时分(yyyy年M月d日 )
	 * 
	 * @return
	 */
	public static String getFormatDate2(Date targetdate) {
		SimpleDateFormat f_target = new SimpleDateFormat("yyyy年M月d日 HH:mm");
		String targetDateTimeStr = f_target.format(targetdate);
		Date nowDate = new Date();
		// 当天显示时分 去掉“YYYY年M月D日 ”
		targetDateTimeStr = targetDateTimeStr.replace(new SimpleDateFormat(
				"yyyy年M月d日 ").format(nowDate), "");
		// 跨天显示月日时分;跨月显示月日时分 去掉（YYYY年）
		targetDateTimeStr = targetDateTimeStr.replace(new SimpleDateFormat(
				"yyyy年").format(nowDate), "");
		// 跨年去掉时间（去掉 HH:mm）
		if (targetDateTimeStr.contains("年") || targetDateTimeStr.contains("日")) {
			targetDateTimeStr = targetDateTimeStr.replace(new SimpleDateFormat(
					" HH:mm").format(targetdate), "");
		}

		return targetDateTimeStr;
	}

	/**
	 * 规则： 格式：yyyy年M月d日 HH:mm 当天显示时分(HH:mm)； 跨天/跨月显示月日时分 (M月d日
	 * HH:mm)；跨年显示年月日时分(yyyy年M月d)
	 * 
	 * @param formate
	 *            例如：yyyyMMdd HH:mm:ss、yyyy-MM-dd HH:mm:ss、yyyy年MM月dd HH:mm:ss
	 * @param dateTime
	 * @return
	 */
	public static String getQyFormateDate(String formate, String dateTime) {
		SimpleDateFormat f_in = new SimpleDateFormat(formate);
		Date inDate = null;
		try {
			inDate = f_in.parse(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (inDate != null) {
			return getFormatDate(inDate);
		} else {
			return null;
		}
	}

	/**
	 * 规则： 格式：yyyy年M月d日 HH:mm 当天显示时分(HH:mm)； 跨天/跨月显示月日时分 (M月d日
	 * )；跨年显示年月日时分(yyyy年M月d)
	 * 
	 * @param formate
	 *            例如：yyyyMMdd HH:mm:ss、yyyy-MM-dd HH:mm:ss、yyyy年MM月dd HH:mm:ss
	 * @param dateTime
	 * @return
	 */
	public static String getFormatDate2(String formate, String dateTime) {
		SimpleDateFormat f_in = new SimpleDateFormat(formate);
		Date inDate = null;
		try {
			inDate = f_in.parse(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (inDate != null) {
			return getFormatDate2(inDate);
		} else {
			return null;
		}
	}

	/**
	 * 获取当前时间 "yyyy-MM-dd HH:mm:ss|SSS"
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss|SSS");
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}

	/**
	 * 获取当前时间 "yyyy-MM-dd HH:mm:ss|SSS"
	 *
	 * @return
	 */
	public static String getCurrentTime(String formatStr) {
		long time = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat(
				formatStr);
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}

	private static long lastTime = 0;

	/**
	 * 获得当前时间和上次调用时的时间差
	 * 
	 * @return
	 */
	public static String getCurrentTimeAndTimeDifference() {
		Date date = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS")
				.format(date);
		StringBuffer sb = new StringBuffer();
		sb.append("当前时间：");
		sb.append(time);
		sb.append(" 与上次执行时间差：");
		sb.append(date.getTime() - lastTime);
		sb.append(" 毫秒。");
		lastTime = date.getTime();
		return sb.toString();
	}
	private static void main(String[] args) {
		System.out.println(DateUtil.getQyFormateDate("yyyy.MM.dd HH:mm:ss",
				"2015.09.17 15:33:44"));
	}
	
	/**
	 * IM气泡使用的时间格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getDialogTimeFormat(Date date) {
		if (date == null) {
			return "";
		}
		Calendar useCalendar = Calendar.getInstance();
		int nowDay = useCalendar.get(Calendar.DATE);
		int nowYear = useCalendar.get(Calendar.YEAR);
		int nowMonth = useCalendar.get(Calendar.MONTH);
		useCalendar.setTime(date);
		int useDay = useCalendar.get(Calendar.DATE);
		int useYear = useCalendar.get(Calendar.YEAR);
		int useMonth = useCalendar.get(Calendar.MONTH);
		int useHour = useCalendar.get(Calendar.HOUR_OF_DAY);

		if (useYear == nowYear) {
			if (useMonth == nowMonth) {
				if (useDay == nowDay) {
					SimpleDateFormat format = new SimpleDateFormat(hhmm);
					StringBuilder builder = new StringBuilder();
					String hourStr = getIntervalTime(useHour);
					builder.append(hourStr);
					builder.append(format.format(date));
					return builder.toString();
				} else {
					if (nowDay - useDay == 1) {
						SimpleDateFormat format = new SimpleDateFormat(hhmm);
						StringBuilder builder = new StringBuilder();
						builder.append("昨天");
						builder.append(" ");
						String hourStr = getIntervalTime(useHour);
						builder.append(hourStr);
						builder.append(format.format(date));
						return builder.toString();
					} else {
						StringBuilder builder = new StringBuilder();
						SimpleDateFormat format = new SimpleDateFormat(mmdd);
						builder.append(format.format(date));
						builder.append(" ");
						String hourStr = getIntervalTime(useHour);
						builder.append(hourStr);
						format = new SimpleDateFormat(hhmm);
						builder.append(format.format(date));
						return builder.toString();
					}
				}
			} else {
				StringBuilder builder = new StringBuilder();
				SimpleDateFormat format = new SimpleDateFormat(mmdd);
				builder.append(format.format(date));
				builder.append(" ");
				String hourStr = getIntervalTime(useHour);
				builder.append(hourStr);
				format = new SimpleDateFormat(hhmm);
				builder.append(format.format(date));
				return builder.toString();
			}
		} else {
			StringBuilder builder = new StringBuilder();
			SimpleDateFormat format = new SimpleDateFormat(yymmdd);
			builder.append(format.format(date));
			builder.append(" ");
			String hourStr = getIntervalTime(useHour);
			builder.append(hourStr);
			format = new SimpleDateFormat(hhmm);
			builder.append(format.format(date));
			return builder.toString();
		}
	}
	/**
	 * 企信列表时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDialogTimeList(Date date) {
		if (date == null) {
			return "";
		}
		Calendar useCalendar = Calendar.getInstance();
		int nowDay = useCalendar.get(Calendar.DATE);
		int nowYear = useCalendar.get(Calendar.YEAR);
		int nowMonth = useCalendar.get(Calendar.MONTH);
		useCalendar.setTime(date);
		int useDay = useCalendar.get(Calendar.DATE);
		int useYear = useCalendar.get(Calendar.YEAR);
		int useMonth = useCalendar.get(Calendar.MONTH);
		int useHour = useCalendar.get(Calendar.HOUR_OF_DAY);

		if (useYear == nowYear) {
			if (useMonth == nowMonth) {
				if (useDay == nowDay) {
					SimpleDateFormat format = new SimpleDateFormat(hhmm);
					StringBuilder builder = new StringBuilder();
					String hourStr = getIntervalTime(useHour);
					builder.append(hourStr);
					builder.append(format.format(date));
					return builder.toString();
				} else {
					if (nowDay - useDay == 1) {
//						SimpleDateFormat format = new SimpleDateFormat(hhmm);
						StringBuilder builder = new StringBuilder();
						builder.append("昨天");
//						builder.append(" ");
//						String hourStr = getIntervalTime(useHour);
//						builder.append(hourStr);
//						builder.append(format.format(date));
						return builder.toString();
					} else {
						StringBuilder builder = new StringBuilder();
						SimpleDateFormat format = new SimpleDateFormat(mmdd);
						builder.append(format.format(date));
//						builder.append(" ");
//						String hourStr = getIntervalTime(useHour);
//						builder.append(hourStr);
//						format = new SimpleDateFormat(hhmm);
//						builder.append(format.format(date));
						return builder.toString();
					}
				}
			} else {
				StringBuilder builder = new StringBuilder();
				SimpleDateFormat format = new SimpleDateFormat(mmdd);
				builder.append(format.format(date));
//				builder.append(" ");
//				String hourStr = getIntervalTime(useHour);
//				builder.append(hourStr);
//				format = new SimpleDateFormat(hhmm);
//				builder.append(format.format(date));
				return builder.toString();
			}
		} else {
			StringBuilder builder = new StringBuilder();
			SimpleDateFormat format = new SimpleDateFormat(yymmdd);
			builder.append(format.format(date));
//			builder.append(" ");
//			String hourStr = getIntervalTime(useHour);
//			builder.append(hourStr);
//			format = new SimpleDateFormat(hhmm);
//			builder.append(format.format(date));
			return builder.toString();
		}
	}
	
	/**
	 * 计算时段
	 * 
	 * @param hour
	 *            小时
	 * @return 时段
	 */
	private static String getIntervalTime(int hour) {
		String intervalTime = "";
		if (hour >= 0 && hour < 6) {
			intervalTime = "凌晨";
		} else if (hour >= 6 && hour < 12) {
			intervalTime = "早上";
		} else if (hour >= 12 && hour < 13) {
			intervalTime = "中午";
		} else if (hour >= 13 && hour < 18) {
			intervalTime = "下午";
		} else {
			intervalTime = "晚上";
		}
		return intervalTime;

	}
}
