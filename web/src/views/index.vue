<template>
  <div>
    <main role="main">


      <section class="jumbotron text-center">

        <div class="container">
          <h1>在线教育</h1>
          <p class="lead text-muted">在线教育平台的编写</p>

        </div>

        <!-- Slider main container -->
        <div class="swiper-container">
          <!-- Additional required wrapper -->
          <div class="swiper-wrapper">
            <!-- Slides -->
            <div class="swiper-slide">Slide 1</div>
            <div class="swiper-slide">Slide 2</div>
            <div class="swiper-slide">Slide 3</div>
            ...
          </div>
          <!-- If we need pagination -->
          <div class="swiper-pagination"></div>

          <!-- If we need navigation buttons -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>

          <!-- If we need scrollbar -->
          <div class="swiper-scrollbar"></div>
        </div>
      </section>



      <div class="album py-5 bg-light">
        <div class="container">
          <div class="title1">最新上线</div>
          <div class="row">
            <div v-for="(o,index) in news" class="col-md-4" v-bind:key="index">
              <the-course v-bind:course="o"></the-course>
            </div>
          </div>

          <hr>

          <div class="title2">好课推荐</div>
          <div class="row">
            <div v-for="(o,index) in news" class="col-md-4" v-bind:key="index">
              <the-course v-bind:course="o"></the-course>
            </div>


          </div>
        </div>
      </div>

    </main>
  </div>

</template>

<script>

import TheCourse from "../components/the-course";


export default {
  name: 'index',
  components: {TheCourse},

  data: function () {
    return {
      news: [],
    }
  },
  mounted() {
    let _this = this;
    _this.listNew();
  },
  methods: {
    /**
     * 查询新上好课
     */
    listNew() {
      let _this = this;

      //放到缓存中
      let news = SessionStorage.get("news");
      //如果不为空
      if (!Tool.isEmpty(news)) {
        //赋值
        _this.news = news;
        return;
      }
      //前端不传参数
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/course/list-new').then((response) => {
        console.log("查询新上好课结果：", response);
        let resp = response.data;
        if (resp.success) {
          _this.news = resp.content;
          // 保存到缓存
          SessionStorage.set("news", _this.news);
        }
      }).catch((response) => {
        console.log("error：", response);
      })
    },
  }
}
</script>

<style>
.title1 {
  margin-bottom: 2rem;
  color: #fafafa;
  letter-spacing: 0;
  text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
  font-size: 2rem;
}

.title2 {
  margin-bottom: 2rem;
  color: transparent;
  -webkit-text-stroke: 1px black;
  letter-spacing: 0.04em;
  font-size: 2rem;
}

.swiper-container {
  width: 600px;
  height: 300px;
}
</style>
