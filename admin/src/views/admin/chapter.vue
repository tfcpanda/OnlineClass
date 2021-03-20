<template>
  <!-- PAGE CONTENT BEGINS -->
  <div>
    <h4 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
    </h4>
    <hr>
    <p>
      <!--返回按钮-->
      <router-link to="/business/course" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-arrow-left"></i>
        返回课程
      </router-link>
      &nbsp;
      <!--新增按钮-->
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">

        <i class="ace-icon fa fa-edit red2"></i>
        新增
      </button>
      <!--刷新按钮-->
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">

        <i class="ace-icon fa fa-refresh red2"></i>
        刷新
      </button>
    </p>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{ chapter.id }}</td>
        <td>{{ chapter.name }}</td>



        <td>
          <div class="hidden-sm hidden-xs btn-group">


            <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              小节
            </button>&nbsp;
            <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              编辑
            </button>&nbsp;
            <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
              删除
            </button>

          </div>
          <div class="hidden-md hidden-lg">
            <div class="inline position-relative">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
                  </a>
                </li>
              </ul>
            </div>
          </div>

        </td>
      </tr>


      </tbody>
    </table>


    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{course.name}}</p>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>
<script>

import Pagination from "../../components/pagination"

export default {
  components: {Pagination},

  name: "chapter",
  data: function () {
    return {
      chapter: {},
      chapters: [],
      course: {},
    }
  },
  mounted: function () {
    let _this = this;
    _this.$refs.pagination.size = 5;
    let course = SessionStorage.get("course") || {};
    if (Tool.isEmpty(course)) {
      _this.$router.push("/welcome");
    }
    _this.course = course;
    _this.list(1);
    //激活样式之一
    // this.$parent.activeSidebar("business-chapter-sidebar");

  },
  methods: {
    /**
     * 增加方法
     */
    add() {
      let _this = this;
      _this.chapter = {};
      $("#form-modal").modal("show");
    },

    /**
     * 编辑方法
     * @param chapter
     */
    edit(chapter) {
      let _this = this;
      _this.chapter = $.extend({}, chapter);
      $("#form-modal").modal("show");

    },

    /**
     *列表查询
     * @param page
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
        page: page,
        size: _this.$refs.pagination.size,
        courseId: _this.course.id

      }).then((response) => {

        Loading.hide();
        console.log("查询大章节列表结果：", response);
        let resp = response.data;
        _this.chapters = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },

    /**
     * 保存
     * @param page
     */
    save(page) {
      let _this = this;

      if (!Validator.require(_this.chapter.name, "名称")) {
          // || !Validator.length(_this.chapter.courseId, "课程Id", 1, 8)
        return;
      }
      _this.chapter.courseId = _this.course.id;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save',
          _this.chapter).then((response) => {
        Loading.hide();
        console.log("增加大章节列表结果：", response);
        let resp = response.data;
        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("新增成功！");

        } else {
          Toast.warning(resp.message);
        }
      })
    },

    /**
     * 根据ID删除
     * @param id
     */
    del(id) {
      let _this = this;
      Confirm.show("删除大章后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response) => {
          Loading.hide();
          console.log("删除大章节列表结果：", response);
          let resp = response.data;
          if (resp.success) {

            _this.list(1);
            Toast.success("删除成功！");
          }
        })

      });

      // Swal.fire({
      //   title: '确认删除?',
      //   text: "删除后不可恢复!",
      //   icon: 'warning',
      //   showCancelButton: true,
      //   confirmButtonColor: '#3085d6',
      //   cancelButtonColor: '#dd3333',
      //   confirmButtonText: 'Yes, delete it!'
      // }).then((result) => {
      //   if (result.isConfirmed) {
      //     Loading.show();
      //     _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response) => {
      //       Loading.hide();
      //       console.log("删除大章节列表结果：", response);
      //       let resp = response.data;
      //       if (resp.success) {
      //         Toast.success("删除成功！");
      //         _this.list(1);
      //
      //       }
      //     })
      //
      //   }
      // })


    },
    /**
     * 点击【小节】
     */
    toSection(chapter) {
      let _this = this;
      SessionStorage.set("chapter", chapter);
      _this.$router.push("/business/section");
    }

  }
}
</script>