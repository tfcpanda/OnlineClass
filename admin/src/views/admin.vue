<template>
  <div>
    <div id="navbar" class="navbar navbar-default">


      <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" >
          <span class="sr-only">Toggle sidebar</span>

          <span class="icon-bar"></span>

          <span class="icon-bar"></span>

          <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
          <a href="#" class="navbar-brand">
            <small>
              <i class="fa fa-leaf"></i>
              后台管理
            </small>
          </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
          <ul class="nav ace-nav">
            <li class="grey">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="ace-icon fa fa-tasks"></i>
                <span class="badge badge-grey">4</span>
              </a>

              <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                <li class="dropdown-header">
                  <i class="ace-icon fa fa-check"></i>
                  4 Tasks to complete
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
                      <span class="pull-left">Software Update</span>
                      <span class="pull-right">65%</span>
                    </div>

                    <div class="progress progress-mini">
                      <div style="width:65%" class="progress-bar"></div>
                    </div>
                  </a>
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
                      <span class="pull-left">Hardware Upgrade</span>
                      <span class="pull-right">35%</span>
                    </div>

                    <div class="progress progress-mini">
                      <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                    </div>
                  </a>
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
                      <span class="pull-left">Unit Testing</span>
                      <span class="pull-right">15%</span>
                    </div>

                    <div class="progress progress-mini">
                      <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                    </div>
                  </a>
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
                      <span class="pull-left">Bug Fixes</span>
                      <span class="pull-right">90%</span>
                    </div>

                    <div class="progress progress-mini progress-striped active">
                      <div style="width:90%" class="progress-bar progress-bar-success"></div>
                    </div>
                  </a>
                </li>

                <li class="dropdown-footer">
                  <a href="#">
                    See tasks with details
                    <i class="ace-icon fa fa-arrow-right"></i>
                  </a>
                </li>
              </ul>
            </li>

            <li class="purple">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                <span class="badge badge-important">8</span>
              </a>

              <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                <li class="dropdown-header">
                  <i class="ace-icon fa fa-exclamation-triangle"></i>
                  8 Notifications
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
												New Comments
											</span>
                      <span class="pull-right badge badge-info">+12</span>
                    </div>
                  </a>
                </li>

                <li>
                  <a href="#">
                    <i class="btn btn-xs btn-primary fa fa-user"></i>
                    Bob just signed up as an editor ...
                  </a>
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
												New Orders
											</span>
                      <span class="pull-right badge badge-success">+8</span>
                    </div>
                  </a>
                </li>

                <li>
                  <a href="#">
                    <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
												Followers
											</span>
                      <span class="pull-right badge badge-info">+11</span>
                    </div>
                  </a>
                </li>

                <li class="dropdown-footer">
                  <a href="#">
                    See all notifications
                    <i class="ace-icon fa fa-arrow-right"></i>
                  </a>
                </li>
              </ul>
            </li>

            <li class="green">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                <span class="badge badge-success">5</span>
              </a>

              <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                <li class="dropdown-header">
                  <i class="ace-icon fa fa-envelope-o"></i>
                  5 Messages
                </li>

                <li class="dropdown-content">
                  <ul class="dropdown-menu dropdown-navbar">
                    <li>
                      <a href="#">
                        <img src="assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar"/>
                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
                      </a>
                    </li>

                    <li>
                      <a href="#">
                        <img src="assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar"/>
                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
                      </a>
                    </li>

                    <li>
                      <a href="#">
                        <img src="assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar"/>
                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
                      </a>
                    </li>

                    <li>
                      <a href="#">
                        <img src="assets/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar"/>
                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
                      </a>
                    </li>

                    <li>
                      <a href="#">
                        <img src="assets/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar"/>
                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
                      </a>
                    </li>
                  </ul>
                </li>

                <li class="dropdown-footer">
                  <a href="inbox.html">
                    See all messages
                    <i class="ace-icon fa fa-arrow-right"></i>
                  </a>
                </li>
              </ul>
            </li>

            <li class="light-blue">
              <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                <img class="nav-user-photo" src="../../public/ace/assets/images/avatars/user.jpg" alt="Jason's Photo"/>
                <span class="user-info">
									<small>Welcome,</small>
									{{ loginUser.name }}
								</span>

                <i class="ace-icon fa fa-caret-down"></i>
              </a>

              <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                <li>
                  <a href="#">
                    <i class="ace-icon fa fa-cog"></i>
                    系统设置
                  </a>
                </li>

                <li>
                  <a href="profile.html">
                    <i class="ace-icon fa fa-user"></i>
                    个人信息
                  </a>
                </li>

                <li class="divider"></li>

                <li>
                  <a v-on:click="logout()" href="#">
                    <i class="ace-icon fa fa-power-off"></i>
                    退出登录
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div><!-- /.navbar-container -->
    </div>

    <div class="main-container" id="main-container">


      <div id="sidebar" class="sidebar">


        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
          <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
              <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
              <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
              <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
              <i class="ace-icon fa fa-cogs"></i>
            </button>
          </div>

          <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
          </div>
        </div><!-- /.sidebar-shortcuts -->

        <ul class="nav nav-list">
          <li class="" id="welcome-sidebar">
            <router-link to="/welcome">
              <i class="menu-icon fa fa-tachometer"></i>
              <span class="menu-text"> 欢迎：{{ loginUser.name }} </span>
            </router-link>

            <b class="arrow"></b>
          </li>

          <li v-show="hasResource('01')" class="">
            <a href="#" class="dropdown-toggle">
              <i class="menu-icon fa fa-desktop"></i>
              <span class="menu-text"> 系统管理 </span>

              <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">


              <li v-show="hasResource('0101')" class="" id="system-user-sidebar">
                <router-link to="/business/user">
                  <i class="menu-icon fa fa-caret-right"></i>
                  管理员管理
                </router-link>
                <b class="arrow"></b>
              </li>

              <li v-show="hasResource('0102')" class="">
                <router-link to="/business/resource">
                  <i class="menu-icon fa fa-caret-right"></i>
                  资源管理
                </router-link>

                <b class="arrow"></b>
              </li>
              <li v-show="hasResource('0103')" class="">
                <router-link to="/business/role">
                  <i class="menu-icon fa fa-caret-right"></i>
                  角色管理
                </router-link>

                <b class="arrow"></b>
              </li>
            </ul>
          </li>


          <li v-show="hasResource('02')" class="">
            <a href="#" class="dropdown-toggle">
              <i class="menu-icon fa fa-desktop"></i>
              <span class="menu-text"> 业务管理 </span>

              <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul v-show="hasResource('0201')" class="submenu">
              <li class="active open" id="business-category-sidebar">
                <router-link to="/business/category">
                  <i class="menu-icon fa fa-caret-right"></i>
                  分类管理
                </router-link>

                <b class="arrow"></b>
              </li>
              <li v-show="hasResource('0202')" class="active open" id="business-course-sidebar">
                <router-link to="/business/course">
                  <i class="menu-icon fa fa-caret-right"></i>
                  课程管理
                </router-link>

                <b class="arrow"></b>
              </li>


              <li v-show="hasResource('0203')" class="active open" id="business-teacher-sidebar">
                <router-link to="/business/teacher">
                  <i class="menu-icon fa fa-caret-right"></i>
                  讲师管理
                </router-link>

                <b class="arrow"></b>
              </li>

              <li v-show="hasResource('0204')" class="active open" id="business-member-sidebar">
                <router-link to="/business/member">
                  <i class="menu-icon fa fa-caret-right"></i>
                  会员管理
                </router-link>

                <b class="arrow"></b>
              </li>

              <li v-show="hasResource('0205')" class="active open" id="business-sms-sidebar">
                <router-link to="/business/sms">
                  <i class="menu-icon fa fa-caret-right"></i>
                  短信管理
                </router-link>

                <b class="arrow"></b>
              </li>


<!--              <li v-show="hasResource('0204')" class="active open" id="business-member-sidebar">-->
<!--                <router-link to="/business/member">-->
<!--                  <i class="menu-icon fa fa-caret-right"></i>-->
<!--                  会员管理-->
<!--                </router-link>-->

<!--                <b class="arrow"></b>-->
<!--              </li>-->
            </ul>
          </li>

<!--     v-show方法，如果能够查到这个值的话，显示，如果查不到的话就隐藏     -->
          <li v-show="hasResource('03')" class="">
            <a href="#" class="dropdown-toggle">
              <i class="menu-icon fa fa-desktop"></i>
              <span class="menu-text"> 文件管理 </span>

              <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">


              <li v-show="hasResource('0301')" class="file-file-sidebar">
                <router-link to="/business/file">
                  <i class="menu-icon fa fa-caret-right"></i>
                  文件管理
                </router-link>

                <b class="arrow"></b>
              </li>
            </ul>
          </li>


        </ul><!-- /.nav-list -->

        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
          <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
             data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>


      </div>

      <div class="main-content">


        <div class="page-content">
          <div class="ace-settings-container" id="ace-settings-container">


            <div class="ace-settings-box clearfix" id="ace-settings-box">
              <div class="pull-left width-50">
                <div class="ace-settings-item">
                  <div class="pull-left">
                    <select id="skin-colorpicker" class="hide">
                      <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                      <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                      <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                      <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                    </select>
                  </div>
                  <span>&nbsp; Choose Skin</span>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                  <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                  <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                  <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                  <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                  <label class="lbl" for="ace-settings-add-container">
                    Inside
                    <b>.container</b>
                  </label>
                </div>
              </div><!-- /.pull-left -->

              <div class="pull-left width-50">
                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"/>
                  <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"/>
                  <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                </div>

                <div class="ace-settings-item">
                  <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"/>
                  <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                </div>
              </div><!-- /.pull-left -->
            </div><!-- /.ace-settings-box -->
          </div><!-- /.ace-settings-container -->

          <div class="row">
            <div class="col-xs-12">
              <!-- PAGE CONTENT BEGINS -->
              <router-view/>
              <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </div><!-- /.page-content -->
      </div><!-- /.main-content -->

      <div class="footer">
        <div class="footer-inner">
          <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">TFC</span>
							毕业设计田付成192820636 &copy; 2020-2021 皖ICP备2021002041号-1
						</span>

            &nbsp; &nbsp;
            <span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
          </div>
        </div>
      </div>

      <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
      </a>
    </div><!-- /.main-container -->
  </div>


</template>

<script>


export default {

  name: "admin",
  data: function () {

    return {
      loginUser: {},
    }
  },
  mounted: function () {
    let _this = this;
    $("body").attr("class", "no-skin");
    $("body").remove("login-layout light-login");
    _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar");

    $.getScript('/ace/assets/js/ace.min.js');

    _this.loginUser = Tool.getLoginUser();

    // if (!_this.hasResourceRouter(_this.$route.name)) {
    //   _this.$router.push("/login");
    // }

    let test = Tool.getLoginUser();
    console.log("内容", test);
  },
  //通过监听，得到老的路由，和新的路由并把它打印出来。
  watch: {
    $route: {
      handler: function (val, oldVal) {
        // sidebar激活样式方法二
        console.log("---->页面跳转：", val, oldVal);
        let _this = this;
        // if (!_this.hasResourceRouter(val.name)) {
        //   _this.$router.push("/login");
        //   return;
        // }
        _this.$nextTick(function () {  //页面加载完成后执行
          _this.activeSidebar(_this.$route.name.replace("/", "-") + "-sidebar");
        })
      }
    }


  },

  methods: {
    // /**
    //  * 查找是否有权限
    //  * @param router
    //  */
    // hasResourceRouter(router) {
    //   let _this = this;
    //   let resources = Tool.getLoginUser().resources;
    //
    //   if (Tool.isEmpty(resources)) {
    //     return false;
    //   }
    //   for (let i = 0; i < resources.length; i++) {
    //     if (router === resources[i].page) {
    //       return true;
    //     }
    //   }
    //   return false;
    // },


    /**
     * 查找是否有权限
     * @param id
     */
    hasResource(id) {
      return Tool.hasResource(id);
    },

    login() {
      this.$router.push("/admin")
    },
    /**
     * 菜单激活样式，id是当前点击的菜单的id
     * @param id
     */
    activeSidebar: function (id) {
      // 兄弟菜单去掉active样式，自身增加active样式
      $("#" + id).siblings().removeClass("active");
      $("#" + id).siblings().find("li").removeClass("active");
      $("#" + id).addClass("active");

      // 如果有父菜单，父菜单的兄弟菜单去掉open active，父菜单增加open active
      let parentLi = $("#" + id).parents("li");
      if (parentLi) {
        parentLi.siblings().removeClass("open active");
        parentLi.siblings().find("li").removeClass("active");
        parentLi.addClass("open active");
      }

    },
    logout() {
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/user/logout').then((response) => {
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          Tool.setLoginUser(null);
          _this.$router.push("/login")
        } else {
          Toast.warning(resp.message)
        }
      });
    },

    show(){
      let _this = this;
      _this.isShow = !_this.isShow;
    },
  }
}



</script>