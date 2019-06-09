package com.twinkle.bookstore.bean;

import java.util.List;

/**
 * @param <T>
 * @Name com.twinkle.bookstore.bean/Page.java
 * @Description: 分页信息信息封装
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
public class Page<T> {
	private int pageNum;// 当前页的页码
	private List<T> list;// 数据对象的集合
	private int totalRecord;// 总记录数
	private int totalPageCount;// 总页数
	private int firstPageNum;// 显示的第一个页码
	private int lastPageNum;// 显示的最后一个页码

	public static final int PAGE_RECORD = 3;// 一页显示的记录数
	public static final int DISPLAY_PAGE_COUNT = 5;// 显示的页码数
	/* 使用奇数 */

	public Page(int pageNum, List<T> list, int totalRecord) {
		super();
		this.pageNum = pageNum;
		this.list = list;
		this.totalRecord = totalRecord;

		this.totalPageCount = (totalRecord + PAGE_RECORD - 1) / PAGE_RECORD;

		if (totalPageCount < DISPLAY_PAGE_COUNT) {
			firstPageNum = 1;
			lastPageNum = totalPageCount;
		} else {

			firstPageNum = pageNum - DISPLAY_PAGE_COUNT / 2;
			lastPageNum = pageNum + DISPLAY_PAGE_COUNT / 2;

			if (firstPageNum < 1) {
				firstPageNum = 1;
				lastPageNum = DISPLAY_PAGE_COUNT;
			}

			if (lastPageNum > totalPageCount) {
				lastPageNum = totalPageCount;
				firstPageNum = totalPageCount - DISPLAY_PAGE_COUNT + 1;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public List<T> getList() {
		return list;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public int getPageRecord() {
		return PAGE_RECORD;
	}

	public int getDisplayPageCount() {
		return DISPLAY_PAGE_COUNT;
	}
}
