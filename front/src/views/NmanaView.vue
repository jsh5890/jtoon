<script setup lang="ts">
import {ref} from 'vue'
import type {TabsPaneContext} from 'element-plus'
import axios from "axios";

const daysWeek = {
  일: 'sun', 월: 'mon', 화: 'tue', 수: 'wed', 목: 'thu', 금: 'fri', 토: 'sat'
}

const nowDay = () => {
  let now = new Date()

  const week = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat']

  let dayOfWeek = week[now.getDay()]
  return dayOfWeek
}

const activeName = ref(nowDay())

let day = ref(nowDay())

const posts = ref([]);

const handleClick = (tab: TabsPaneContext, event: Event) => {
  posts.value = [];
  day.value = <string>tab.props.name
  axios.get("/api/jtoon/weekdayList/" + day.value).then((response) => {
    response.data.forEach((r: any) => {
      // console.log(r)
      posts.value.push(r);
    });
  })
}

axios.get("/api/jtoon/weekdayList/" + day.value).then((response) => {
  response.data.forEach((r: any) => {
    // console.log(r)
    posts.value.push(r);
  });
})

</script>

<template>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
    <el-tab-pane v-for=" (value , key) in daysWeek" v-bind:label="key" v-bind:name="value">
      <ul class="img_list">
        <li v-for="post in posts" :key="post.title">
          <div class="thumb">
            <router-link :to="{ name: 'nDetail', params: { href: post.href } }" v-bind:title="post.title">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   v-bind:src="post.src" v-bind:title="post.title" v-bind:alt="post.title">
            </router-link>
          </div>
          <dl>
            <dt>
              <router-link :to="{ name: 'nDetail', params: { href: post.href } }" v-bind:title="post.title">
                {{ post.title }}
              </router-link>

            </dt>
            <dd class="desc">
              {{ post.writer }}
            </dd>
            <dd class="more">
              <router-link :to="{ name: 'nDetail', params: { href: post.href } }">전체보기</router-link>
            </dd>
          </dl>
        </li>
      </ul>
    </el-tab-pane>
  </el-tabs>
</template>
<style scoped>
body, input, textarea, select, button, table {
  font-size: 12px;
  font-family: Arial, '나눔고딕', NanumGothic, -apple-system, BlinkMacSystemFont, 'Apple SD Gothic Neo', '맑은고딕', MalgunGothic, '돋움', Dotum, Sans-serif;
  color: #434343;
}

.img_list {
  padding: 30px 0 5px;
  border-bottom: 1px solid #eaeaea;
  overflow: hidden;
  padding-left: 1px;
  zoom: 1;
  list-style: none;
  font-size: 12px;
}

.img_list li {
  float: left;
  width: 231px;
  line-height: 17px;
}

.img_list li .thumb {
  float: left;
  margin-right: 11px;
  position: relative;
  margin-bottom: 3px;
}

.img_list li .thumb a {
  display: block;
  position: relative;
}

.img_list li .thumb a img {
  width: 83px;
  height: 90px;
}

a:hover {
  text-decoration: underline;
}

a {
  color: black;
  text-decoration: none;
}

a:visited {
  color: black;
  text-decoration: none;
}
</style>