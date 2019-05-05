import {jsonpGet} from "@/api/ajax";
/*-------------------------router面包屑-start---------------------------------------*/
const routerBreadcrumb = {
	
  // 内容管理-文章管理
  articleList: {
    list: [
      {name: "内容管理", path: ""},
      {name: "文章管理", path: "/articleList"}
    ]
  },
  articleDetail: {
    list: [
      {name: "内容管理", path: ""},
      {name: "文章管理", path: "/articleList"},
      {name: "编辑", path: "/articleDetail"}
    ]
  },
  // 内容管理-首页管理
  indexList: {
    list: [
      {name: "内容管理", path: ""},
      {name: "首页管理", path: "/indexList"}
    ]
  },  
}
/*-------------------------router面包屑-end---------------------------------*/
export {
    routerBreadcrumb,
    // ...
}