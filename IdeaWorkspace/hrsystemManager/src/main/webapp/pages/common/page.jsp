<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<%-- 分页的开始 --%>
		<div id="page_nav">
			<c:if test="${ page.pageNo > 1 }">
				<a href="${ page.url }?pageNo=1">首页</a>
				<a href="${page.url }?pageNo=${ page.pageNo - 1 }">上一页</a>
			</c:if>
			<c:choose>
				<%-- 当前总页码小于等于5的情况 --%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${page.pageTotal }" />
				</c:when>
				<%-- 当前总页码大于5的情况 --%>
				<c:otherwise>
					<c:choose>
						<%-- 当前页码为前面三个 --%>
						<c:when test="${page.pageNo <= 3 }">
							<c:set var="begin" value="1" />
							<c:set var="end" value="5" />
						</c:when>
						<%-- 当前页码为最后三个 --%>
						<c:when test="${ page.pageNo >= page.pageTotal-2 }">
							<c:set var="begin" value="${page.pageTotal - 4 }" />
							<c:set var="end" value="${ page.pageTotal }" />
						</c:when>
						<%-- 中间的页码 --%>
						<c:otherwise>
							<c:set var="begin" value="${ page.pageNo - 2}" />
							<c:set var="end" value="${ page.pageNo + 2 }" />
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

					<c:forEach begin="${ begin }" end="${ end }" var="i">
						<c:if test="${ i == page.pageNo }">
							【${ i }】
						</c:if>
						<c:if test="${ i != page.pageNo }">
							<a href="${ page.url }?pageNo=${ i }">${ i }</a>
						</c:if>
					</c:forEach>	



			<c:if test="${ page.pageNo < page.pageTotal  }">
				<a href="${ page.url }?pageNo=${page.pageNo + 1 }">下一页</a>
				<a href="${ page.url }?pageNo=${page.pageTotal }">末页</a>
			</c:if>
			共${page.pageTotal }页，${page.pageTotalCount }条记录 到第
			<input value="${ page.pageNo }" name="pn" id="pn_input"/>页
			<input type="button" id="seach_btn" value="确定">
		</div>
		<script type="text/javascript">
			$(function(){
				$("#seach_btn").click(function(){
					//1.获取输入框中的页码
					var pageNo = $("#pn_input").val();
					//2.修改浏览器为当前指定的页（需要搜索的页码）;
					// js 当中提交一个location对象。有一个属性叫href属性，可以直接读取，或设置 当前浏览器地址栏的地址。
					// 相对路径
// 					location.href = "${ requestScope.page.url }&pageNo=" + pageNo;
					// 绝对路径
					location.href = "${requestScope.path}${ requestScope.page.url }?pageNo=" + pageNo;
				});
			});
		</script>
		<%-- 分页的结束 --%>    
    
    
