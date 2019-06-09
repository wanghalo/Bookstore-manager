package com.twinkle.bookstore.mytab;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.twinkle.bookstore.dao.BookDao;
import com.twinkle.bookstore.dao.impl.BookDaoImpl;

/**
 * @Name com.twinkle.bookstore.mytab/CheckCategoryTag.java
 * @Description: 自定义标签 <CheckCategory id=${}> ${content}
 *
 * @VersionInformation: Twinkle 2019年5月21日 V1.0
 */
public class CheckCategoryTag extends SimpleTagSupport {

	
	private BookDao bookDao = new BookDaoImpl();
	
	private String id;
	
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void doTag() throws JspException, IOException {
		boolean has = bookDao.hasBookByCategorysId(id);
		JspFragment fragment = getJspBody();
		StringWriter writer = new StringWriter();
		fragment.invoke(writer);
		String result = writer.toString();
		if(has) {
			result = "<button type=\"button\" class=\"btn btn-danger btn-block btn-lg\" disabled=\"disabled\">删除</button>";
		}
		getJspContext().getOut().write(result);
	}
}
