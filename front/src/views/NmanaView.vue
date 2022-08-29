<script setup lang="ts">
import {ref} from 'vue'
import type {TabsPaneContext} from 'element-plus'


const nowDay = () => {
  let now = new Date()

  const week = ['sun', 'mon', 'tue', 'wed', 'thu', 'fir', 'sat']

  let dayOfWeek = week[now.getDay()]
  return dayOfWeek
}

const activeName = ref(nowDay())

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab.props.name)
}

</script>

<template>
  <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
    <el-tab-pane label="월" name="mon">
      <ul class="img_list">

        <li>
          <div class="thumb">
            <a href="/webtoon/list?titleId=758037&weekday=mon" title="참교육">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   src="https://shared-comic.pstatic.net/thumb/webtoon/758037/thumbnail/thumbnail_IMAG10_a2297504-4912-4c7e-a5a8-524d6fc77103.jpg"
                   width="83" height="90" title="참교육" alt="참교육">
            </a>
          </div>
          <dl>
            <dt>
              <a href="/webtoon/list?titleId=758037&weekday=mon" title="참교육">참교육</a>
            </dt>
            <dd class="desc">
              채용택 / 한가람
            </dd>
            <dd class="more"><a href="/webtoon/list?titleId=758037&weekday=mon">전체보기</a></dd>
          </dl>
        </li>

        <li>
          <div class="thumb">
            <a href="/webtoon/list?titleId=648419&weekday=mon" title="뷰티풀 군바리">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   src="https://shared-comic.pstatic.net/thumb/webtoon/648419/thumbnail/thumbnail_IMAG10_1421195d-13be-4cde-bcf9-0c78d51c5ea3.jpg"
                   width="83" height="90" title="뷰티풀 군바리" alt="뷰티풀 군바리">
            </a>
          </div>
          <dl>
            <dt>
              뷰티풀 군바리
            </dt>
            <dd class="desc">
              <a href="#" onclick="return artistAction.viewArtist('648419', this)">설이 / 윤성원</a>
            </dd>
            <dd class="more"><a href="/webtoon/list?titleId=648419&weekday=mon">전체보기</a></dd>
          </dl>
        </li>

        <li>
          <div class="thumb">
            <a href="/webtoon/list?titleId=183559&weekday=mon" title="신의 탑">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   src="https://shared-comic.pstatic.net/thumb/webtoon/183559/thumbnail/thumbnail_IMAG10_9a752bec-9ebd-4214-9449-28cf4defc650.jpg"
                   width="83" height="90" title="신의 탑" alt="신의 탑">
            </a>
          </div>
          <dl>
            <dt>
              <a href="/webtoon/list?titleId=183559&weekday=mon" title="신의 탑">신의 탑</a>
            </dt>

            <dd class="desc">
              SIU
            </dd>
            <dd class="more"><a href="/webtoon/list?titleId=183559&weekday=mon">전체보기</a></dd>
          </dl>
        </li>

        <li>
          <div class="thumb">
            <a href="/webtoon/list?titleId=783052&weekday=mon" title="퀘스트지상주의">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   src="https://shared-comic.pstatic.net/thumb/webtoon/783052/thumbnail/thumbnail_IMAG10_f455d5d8-c655-43d7-8966-94916efddcc7.jpg"
                   width="83" height="90" title="퀘스트지상주의" alt="퀘스트지상주의">
            </a>
          </div>
          <dl>
            <dt>
              <a href="/webtoon/list?titleId=783052&weekday=mon" title="퀘스트지상주의">퀘스트지상주의</a>
            </dt>

            <dd class="desc">
              박태준 만화회사
            </dd>
            <dd class="more"><a href="/webtoon/list?titleId=783052&weekday=mon">전체보기</a></dd>
          </dl>
        </li>

        <li>
          <div class="thumb">
            <a href="/webtoon/list?titleId=602910&weekday=mon" title="윈드브레이커">
              <img onerror="this.src='https://ssl.pstatic.net/static/comic/images/migration/common/blank.gif'"
                   src="https://shared-comic.pstatic.net/thumb/webtoon/602910/thumbnail/thumbnail_IMAG10_062ee6b8-cfc8-4699-a08a-80091aed33c7.jpg"
                   width="83" height="90" title="윈드브레이커" alt="윈드브레이커">

            </a>
          </div>
          <dl>
            <dt>
              <a href="/webtoon/list?titleId=602910&weekday=mon" title="윈드브레이커">윈드브레이커</a>
            </dt>

            <dd class="desc">
              조용석
            </dd>

            <dd class="more"><a href="/webtoon/list?titleId=602910&weekday=mon">전체보기</a></dd>
          </dl>
        </li>
      </ul>
    </el-tab-pane>
    <el-tab-pane label="화" name="tue">화</el-tab-pane>
    <el-tab-pane label="수" name="wed">수</el-tab-pane>
    <el-tab-pane label="목" name="thu">목</el-tab-pane>
    <el-tab-pane label="금" name="fir">금</el-tab-pane>
    <el-tab-pane label="토" name="sat">토</el-tab-pane>
    <el-tab-pane label="일" name="sun">일</el-tab-pane>
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