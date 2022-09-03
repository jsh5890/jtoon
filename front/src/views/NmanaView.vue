<script setup lang="ts">
import {ref} from 'vue'
import type {TabsPaneContext} from 'element-plus'
import axios from "axios";
import {Buffer} from "buffer"

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
      r['href'] = Buffer.from(r['href']).toString('base64')
      posts.value.push(r);
    });
  })
}

axios.get("/api/jtoon/weekdayList/" + day.value).then((response) => {
  response.data.forEach((r: any) => {
    // console.log(r)
    r['href'] = Buffer.from(r['href']).toString('base64')
    posts.value.push(r);
  });
})

</script>

<template>
  <div id="container">
    <div id="content" class="webtoon">
      <div class="list_area daily_img">
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
      </div>
    </div>
  </div>
</template>
<style scoped>


</style>