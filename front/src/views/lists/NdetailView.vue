<script lang="ts" setup>
import {defineProps, ref} from 'vue';
import axios from 'axios';
import {Buffer} from "buffer";

const props = defineProps({
  href: {
    type: [String, String],
    require: true,
  },
});

const info = ref({});
const detailList = ref([]);
const currentPage = ref(1);
let url = ref('');
url = Buffer.from(props.href, 'base64').toString();
const totalCnt = ref(0)
axios.get("/api/jtoon/list/", {params: {href: url}}).then((response) => {
  // console.log(response.data)
  info.value = response.data.info
  console.log(info.value)
  response.data.detailList.forEach((r) => {
    detailList.value.push(r)
  });
}).catch(function (error) {
  console.log(error);
})

const page = (e) => {
  console.log("e", e)
  // 리스트 초기화
  detailList.value = []
  axios.get("/api/jtoon/listpage/", {params: {href: url, pageNo: e}}).then((response) => {
    console.log(response.data)
    response.data.forEach((r) => {
      detailList.value.push(r)
    });
  }).catch(function (error) {
    console.log(error);
  })
}

</script>

<template>
  <div id="container">
    <div id="content" class="webtoon">
      <!-- body -->
      <!-- 상세 정보영역 -->
      <div class="comicinfo">
        <div class="thumb">
          <img v-bind:src="info.infoImg" width="125" height="101">
        </div>

        <div class="detail">
          <h2>
            <span class="title">{{ info.title }}</span>
            <span class="wrt_nm">{{ info.infoWrtNm }}</span>
          </h2>
        </div>
      </div>
      <!-- //상세 정보영역 -->
      <!-- 리스트 -->
      <table class="viewList">
        <caption><span class="blind">회차별 목록</span></caption>
        <colgroup>
          <col width="99">
          <col width="*">
          <col width="141">
          <col width="76">
        </colgroup>
        <thead>
        <tr>
          <th scope="col">이미지</th>
          <th scope="col">제목</th>
          <th scope="col">등록일</th>
        </tr>
        </thead>
        <tbody>

        <tr v-for="detailLists in detailList">
          <td>
            <a v-bind:href="detailLists.href">
              <img v-bind:src="detailLists.src" width="71" height="41">
            </a>
          </td>
          <td class="title">
            <a v-bind:href="detailLists.href">{{ detailLists.title }}</a>
          </td>

          <td class="num">{{ detailLists.date }}</td>
        </tr>

        </tbody>
      </table>
      <!-- //리스트 -->

    <el-pagination layout="prev, pager, next" v-bind:total="info.totalCnt"
                   v-model:current-page="currentPage"
                   @click.prevent="page(currentPage)"
                   style="display: inline-flex"/>
    </div>


  </div>
</template>
<style>
.el-pagination {
  position: relative;
  margin-top: 15px;
  width: 330px;
  float: left;
}
</style>