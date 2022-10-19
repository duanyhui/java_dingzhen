<%--
  Created by IntelliJ IDEA.
  User: 31165
  Date: 2022/10/19
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html><body>

<c:set value="一二三四五六"  var="s" />

<c:set value="1" var="k" />

<c:forEach items="${paper}"  var="st">

    <font size=4 face="黑体" color=red>

            ${fn:substring(s,k-1,k)}. ${st.name}</font><br>

    <c:set value="1" var="x" />

    <c:forEach items="${st.content}"  var="question">

        <table  width="98%" align=center style="word-break:break-all"><tr>

            <td align=left valign=top width="6%" rowspan=2>

                <b><font color=blue>${x}.</font></b></td>

            <td colspan=4><pre><font >${question["content"]}</font></pre> </td></tr>

            <tr><td align=center valign=top width="15%">

                <b><font color=blue>标准答案:</font></b></td>

                <td align=left width="32%" >

                    <pre><font >${question["answer"]}</font></pre></td>

                <td align=center valign=top width="15%">

                    <b><font color=blue>学生解答：</font></b></td>

                <td align=left width="32%" >

                    <c:if test='${question["answer"]!=question["solution"]}'>

                        <pre><font color="red" >${question["solution"]}</font> &nbsp;</pre>

                    </c:if>

                    <c:if test='${question["answer"]==question["solution"]}'>

                        <pre>${question["solution"]} &nbsp;</pre>

                    </c:if>

                </td></tr>

        </table>

        <c:set var="x" value="${x+1}"/>

    </c:forEach>

    <c:set var="k" value="${k+1}"/><br>

</c:forEach>

</body></html>