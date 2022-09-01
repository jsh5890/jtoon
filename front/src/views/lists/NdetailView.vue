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

axios.get("/api/jtoon/list/" , {params:{ href: props.href}}).then((response) => {
  console.log(response)
})

</script>

<template>
  {{ props.href }}

<!--  <tr>-->
<!--    <td>-->
<!--      <a href="/webtoon/detail?titleId=747269&amp;no=118&amp;weekday=wed" onclick="nclk_v2(event,'lst.img','747269','118')">-->
<!--        <img src="https://shared-comic.pstatic.net/thumb/webtoon/747269/118/thumbnail_202x120_c8e3d3ec-820b-4f87-9e7a-0fb90dc8eba0.jpg" title="117. Ep. 23 버려진 세계 (1)" alt="117. Ep. 23 버려진 세계 (1)" width="71" height="41" onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/non71_41.gif'">-->
<!--        <span class="mask"></span>-->
<!--      </a>-->
<!--    </td>-->
<!--    <td class="title">-->
<!--      <a href="/webtoon/detail?titleId=747269&amp;no=118&amp;weekday=wed" onclick="nclk_v2(event,'lst.title','747269','118')">117. Ep. 23 버려진 세계 (1)</a>-->
<!--      <img src="https://ssl.pstatic.net/static/comic/images/2012/ico_toonup.png" width="27" height="15" alt="UP">-->
<!--    </td>-->
<!--    <td>-->
<!--      <div class="rating_type">-->
<!--        <span class="star"><em style="width:97.53%">평점</em></span>-->
<!--        <strong>9.75</strong>-->
<!--      </div>-->
<!--    </td>-->
<!--    <td class="num">2022.08.30</td>-->
<!--  </tr>-->
</template>
<style>

</style>