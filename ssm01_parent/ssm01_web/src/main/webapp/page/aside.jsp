<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>xxx</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>乐器介绍</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li id="system-setting1"><a
						href="${pageContext.request.contextPath}/user/findAll.do"> <i
							class="fa fa-circle-o"></i> 吉他
					</a></li>
					<li id="system-setting3"><a
						href="${pageContext.request.contextPath}/permission/findAll.do">
							<i class="fa fa-circle-o"></i> 钢琴
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 钢琴
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 口琴
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 二胡
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 古筝
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 琵琶
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 萨克斯
					</a></li>
					<li id="system-setting4"><a
							href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
							class="fa fa-circle-o"></i> 手风琴
					</a></li>
<%--					吉他、长笛、钢琴、小提琴、架子鼓、口琴、二胡、古筝、琵琶、萨克斯、手风琴--%>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>乐器推荐</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting5"><a
						href="${pageContext.request.contextPath}/page/old_user.jsp">
							<i class="fa fa-circle-o"></i> 老用户推荐
					</a></li>
					<li id="system-setting5"><a
							href="${pageContext.request.contextPath}/page/new_user.jsp">
						<i class="fa fa-circle-o"></i> 新用户推荐
					</a></li>
<%--					<li id="system-setting"><a--%>
<%--						href="${pageContext.request.contextPath}/orders/findAll.do?page=1&size=4"> <i--%>
<%--							class="fa fa-circle-o"></i> 订单管理--%>
<%--					</a></li>--%>

				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>