<template>
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="ace-icon fa fa-leaf green"></i>
                <span class="red">Ace</span>
                <span class="white" id="id-text2">Application</span>
              </h1>
              <h4 class="blue" id="id-company-text">&copy; Company Name</h4>
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box" class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="ace-icon fa fa-coffee green"></i>
                      请输入用户名密码
                    </h4>

                    <div class="space-6"></div>

                    <form>
                      <fieldset>
                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">

															<input v-model="user.loginName" type="text" class="form-control" placeholder="用户名"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                        </label>

                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input v-model="user.password" type="password" class="form-control" placeholder="密码"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                        </label>

                        <div class="space"></div>

                        <div class="clearfix">
                          <label class="inline">
                            <input type="checkbox" class="ace"/>
                            <span class="lbl"> Remember Me</span>
                          </label>

                          <button type="button"
                                  class="width-35 pull-right btn btn-sm btn-primary"
                                  v-on:click="login()">
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">Login</span>
                          </button>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                    </form>


                  </div><!-- /.widget-main -->


                </div><!-- /.widget-body -->
              </div><!-- /.login-box -->


            </div><!-- /.position-relative -->

            <div class="navbar-fixed-top align-right">
              <br/>
              &nbsp;
              <a id="btn-login-dark" href="#">Dark</a>
              &nbsp;
              <span class="blue">/</span>
              &nbsp;
              <a id="btn-login-blur" href="#">Blur</a>
              &nbsp;
              <span class="blue">/</span>
              &nbsp;
              <a id="btn-login-light" href="#">Light</a>
              &nbsp; &nbsp; &nbsp;
            </div>
          </div>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.main-content -->
  </div><!-- /.main-container -->
</template>


<script>

export default {

  name: "login",
  data: function () {
    return {
      user: {},
    }
  },
  mounted: function () {
    $("body").attr("class", "no-skin");
    $("body").remove("login-layout light-login");
    console.log("login")
  },
  methods: {
    login() {
      // let _this = this;
      // _this.user.password = hex_md5(_this.user.password + KEY);
      // Loading.show();
      // _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', _this.user).then((response) => {
      //   Loading.hide();
      //   let resp = response.data;
      //   if (resp.success) {
      //     console.log(resp.content());
      //     _this.$router.push("/welcome")
      //   } else {
      //     Toast.warning(resp.message);
      //   }
      let _this = this;

      _this.user.password = hex_md5(_this.user.password + KEY);
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', _this.user).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          console.log("登录成功：",resp.content);
          Tool.setLoginUser(resp.content);
          _this.$router.push("/welcome")
        } else {
          Toast.warning(resp.message)
        }
      });

    }
  }
}
</script>


