<template>
  <div class="main">
    <div class="swiper-contain">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="/static/img/bg.jpg" alt="水下初音"/>
        </div>
        <div class="swiper-slide">
          <img src="/static/img/yourname.png" alt="你的名字"/>
        </div>
        <div class="swiper-slide">
          <img src="/static/img/Rita-01.jpg" alt="丽塔"/>
        </div>
        <div class="swiper-slide">
          <img src="/static/img/Rita-03.jpg" alt="丽塔"/>
        </div>
      </div>
      <!-- 分页符 -->
      <div class="swiper-pagination"></div>
      <!-- 前进后退按钮 -->
      <div class="swiper-button-prev"></div>
      <div class="swiper-button-next"></div>
    </div>
  </div>
</template>

<script>
import Swiper from "swiper";
import "swiper/dist/css/swiper.min.css";
export default {
  data() {
    return {
      activeClass: ".swiper-contain" //滑动模块名称
    };
  },
  mounted() {
    this.swiper = new Swiper(this.activeClass, {
      autoplay: true,    //自动播放
      loop: true,        //循环播放
      delay: 3000,       //每张图间隔三秒
//分页器
      pagination: {
        el: ".swiper-pagination"
      },
//左右前进后退按钮
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
      }
    });
  },
  created() {
  },
  methods: {}
};
</script>

<style>
.swiper-slide {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 0 0.8rem;
}
img {
  width: 100%;
  height: 100%;
}


</style>