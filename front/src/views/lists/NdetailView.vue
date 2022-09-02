<script lang="ts" setup>

import {defineProps} from 'vue';
import axios from "axios";

const props = defineProps({
  href: {
    type: [String, String],
    require: true,
  },
});
console.log(props)

axios.get("/api/jtoon/list/", {params: {href: props.href}}).then((response) => {
  console.log(response)
})

</script>

<template>

  {{ props.href }}

  <div id="content" class="webtoon">

    <!-- body -->
    <!-- 상세 정보영역 -->
    <div class="comicinfo" style="">
      <div class="thumb">
        <a href="/webtoon/list?titleId=798173">
          <img
              src="https://shared-comic.pstatic.net/thumb/webtoon/798173/thumbnail/thumbnail_IMAG06_bf76337a-aecc-4c07-88b9-c0f8d35115da.jpg"
              title="이상한 변호사 우영우" alt="이상한 변호사 우영우" width="125" height="101">
          <span class="mask"></span>
        </a>
      </div>
      <div class="detail">
        <h2>
          <span class="title">이상한 변호사 우영우</span>
          <span class="wrt_nm">유일 / 화음조,이예지</span>
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

      <tr>
        <td>
          <a href="/webtoon/detail?titleId=798173&amp;no=6&amp;weekday=thu">
            <img src="https://shared-comic.pstatic.net/thumb/webtoon/798173/6/thumbnail_202x120_3eb0d799-defc-43c7-9ad8-936b4be38acc.jpg"
                title="6화: 이상한 변호사 우영우 (6)" width="71" height="41">
            <span class="mask"></span>
          </a>
        </td>
        <td class="title">
          <a href="/webtoon/detail?titleId=798173&amp;no=6&amp;weekday=thu">6화: 이상한 변호사 우영우 (6)</a>
        </td>

        <td class="num">2022.08.31</td>
      </tr>

      </tbody>
    </table>
    <!-- //리스트 -->

  </div>
</template>
<style>

</style>