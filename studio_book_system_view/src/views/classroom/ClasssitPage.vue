<template>
  <div>
    <div>
      <!--      新建/edit角色弹窗开始-->
      <el-button type="primary" @click="dialogFormVisible = true"
      >+ 新增
      </el-button
      >
      <!--      搜索框-->


      <el-select
        v-model="queryClasssitCondition.syhClassroomId"
        style="width: 180px"
        placeholder="选择工作室编号"
      >
        <el-option
          v-for="item in classroomNumberList"
          :key="item.syhClassroomId"
          :label="item.syhClassroomNumber"
          :value="item.syhClassroomId"
        >
        </el-option>
      </el-select>

      <el-input
        style="width: 220px;"
        placeholder="请输入工位名"
        v-model="queryClasssitCondition.syhClasssitName"
        prefix-icon="el-icon-search"
        type="text"
        clearable
      >
      </el-input>

      <el-button
        type="primary"
        plain
        @click="searchBtnClick"
        style="margin-left: 20px"
      >查询
      </el-button
      >
      <el-button type="success" plain @click="searchResetBtnClick"
      >重置
      </el-button
      >

      <el-dialog
        title="新增/编辑工作室信息"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true"
      >

        <el-form
          :model="classsitListForm"
          :rules="rules"
          ref="classsitListForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="所属工作室" prop="syhRoleStatus">
            <el-select
              v-model="classsitListForm.syhClassroomId"
              placeholder="请选择所属工作室"
            >
              <el-option
                v-for="item in classroomNumberList"
                :key="item.syhClassroomId"
                :label="item.syhClassroomNumber"
                :value="item.syhClassroomId"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="工位名称" prop="syhSitName">
            <el-input v-model="classsitListForm.syhSitName"></el-input>
          </el-form-item>
          <!-- <el-form-item label="工位编号" prop="syhClassroomNumber">
            <el-input v-model="classsitListForm.syhClassroomNumber"></el-input>
          </el-form-item> -->

          <el-form-item label="工位状态" prop="syhRoleStatus">
            <el-select
              v-model="classsitListForm.syhSitStatus"
              placeholder="请选择工位状态"
            >
              <el-option
                v-for="item in status"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('classsitListForm')"
            >保存
            </el-button
            >
            <el-button @click="resetForm('classsitListForm')">取消</el-button>
          </el-form-item>
        </el-form>
        <!--        <div slot="footer" class="dialog-footer">-->
        <!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <!--          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>-->
        <!--        </div>-->
      </el-dialog>
      <!--      新建角色弹窗end-->
    </div>
    <div style="margin-top: 20px"></div>
    <!--table-->
    <el-table
      ref="multipleTable"
      border
      :data="classsitList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="date" label="序号" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="syhClassroomNumber" label="工作室编号" width="180">
      </el-table-column>
      <el-table-column prop="syhSitName" label="工位名称"></el-table-column>
      <el-table-column
        prop="syhSitUpdateTime"
        label="添加时间"
      ></el-table-column>
      <el-table-column prop="syhSitStatus" label="当前状态">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.syhSitStatus == 0">
            空闲
          </el-tag>
          <el-tag type="danger" v-if="scope.row.syhSitStatus == 1"
          >已占用
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button @click="updateClick(scope.row)" size="small"
          >编辑
          </el-button>

          <el-button
            @click="handleDeleteClick(scope.row)"
            type="danger"
            size="small"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="bottom" style="margin-bottom: 0;margin-top: 10px">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryClasssitCondition.pageNum"
        :page-sizes="[8, 10, 20]"
        :page-size="queryClasssitCondition.pageSize"
        :background="true"
        :pager-count="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        class="pageControl"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  name: '',
  props: [''],
  data () {
    return {
      classsitList: [
        // {
        //   syhSitId: null,
        //   syhClassroomId: null,
        //   syhClassroomNumber: '',
        //   syhSitName: '',
        //   syhSitStatus: null,
        //   syhSitUpdateTime: ''
        // }
      ],
      classsitListForm: {
        syhSitId: null,
        syhClassroomId: null,
        syhClassroomNumber: '',
        syhSitName: '',
        syhSitStatus: null,
        syhSitUpdateTime: ''
      },
      classsitListFormInit: {
        syhSitId: null,
        syhClassroomId: null,
        syhClassroomNumber: '',
        syhSitName: '',
        syhSitStatus: null,
        syhSitUpdateTime: ''
      },
      queryClasssitCondition: {
        pageNum: 1,
        pageSize: 10,
        syhClassroomId: null,
        syhClasssitName: null
      },
      syhClassroomType: null,
      status:[
        {
          value:0,
          label:"空闲"
        },{
          value:1,
          label:"已占用"
        },
      ],
      classroomNumberList: [],
      total: 0,
      dialogFormVisible: false,
      rules: {
        //表单验证规则
        syhClassroomId: [
          { required: true, message: "请选择所属工作室", trigger: "blur" },
        ],
        syhSitName: [
          { required: true, message: "请输入座位名", trigger: "blur" },
          // {
          //   type: "number",
          //   message: "座位名必须为数字值"
          // }
        ],
        // roleCreateTime: [
        //   {required: true, message: '请选择角色状态', trigger: 'blur'}
        // ],
        syhSitStatus: [
          { required: true, message: "请选择工位状态", trigger: "blur" }
        ]
      },
    }
  },
  mounted () {
    this.$emit('getMessage', ['场地管理', '工作室管理'])
    this.load()
  },
  methods: {
    load () {
      this.$http
        .post(
          '/classsit/querySitByClasssitByCondition',
          this.queryClasssitCondition
        )
        .then(res => {
          this.classsitList = res.data.data.result
          this.total = res.data.data.total
          console.log('res', res)
          console.log('this.roleList', this.classsitList)
        })
      this.$http.post('/classroom/queryClassroomAbleSits', null).then(res => {
        this.classroomNumberList = res.data.data.result

        console.log('res', res)
        console.log('this.roleList', this.classroomNumberList)
      })
    },
    searchBtnClick () {
      //根据条件查询
      // console.log('time change', this.dateTime)
      // this.queryClasssitCondition.syhClassroomName=this.dateTime
      this.queryClasssitCondition.pageNum = 1

      console.log('queryClasssitCondition', this.queryClasssitCondition)
      this.$http
        .post(
          '/classsit/querySitByClasssitByCondition',
          this.queryClasssitCondition
        )
        .then(res => {
          this.classsitList = []
          this.classsitList = res.data.data.result
          this.total = res.data.data.total
          this.pageNum = res.data.data.pages
          console.log('res', res)
          console.log('this.tableData', this.classsitList)
        })
    },
    searchResetBtnClick () {
      //重置搜索条件
      this.queryClasssitCondition.pageNum = 1
      this.queryClasssitCondition.syhClassroomId = null
      this.queryClasssitCondition.syhClasssitName = null

      this.load()
    },
    handleSelectionChange (val) {
      //多选
      // if (val.length>0){
      //   this.multipleDeleteButton=false;
      // }else {
      //   this.multipleDeleteButton=true;
      // }
      // this.multipleSelection = val;
      console.log('multipleSelection', this.multipleSelection)
    },
    handleSizeChange: function (size) {
      //选择 一页展示多少数据时 调用的方法
      this.queryClasssitCondition.pageSize = size
      console.log(size + '****')
      this.queryClasssitCondition.pageNum = 1
      this.load()
    },
    handleCurrentChange: function (currentPage) {
      //点击页面项 的函数响应
      this.queryClasssitCondition.pageNum = currentPage
      console.log(
        'this.currentPage = currentPage;',
        this.queryClasssitCondition.pageNum
      )
      this.load()
    },
    updateClick (rows) {
      console.log('updateClick', rows)
      this.classsitListForm = { ...rows }
      this.classsitListForm.syhClassroomType = ''
      console.log('updateClick,roleForm', this.classsitListForm)
      this.dialogFormVisible = true
    },
    submitForm (classsitListForm) {
      //添加/更新工位
      this.$refs[classsitListForm].validate(valid => {
        if (valid) {
          console.log('classsitListForm', this.classsitListForm)
          if (
            this.classsitListForm.syhSitId == null ||
            this.classsitListForm.syhSitId == ''
          ) {
            //此处id为空，为新增操作
            this.$http
              .post('/classsit/saveOrUpdateClasssit', this.classsitListForm)
              .then(res => {
                console.log('res', res)
                this.load()
                if (res.data.code == 200) {
                  this.$refs[classsitListForm].resetFields()
                  setTimeout(
                    this.$message({
                      message: '操作成功',
                      type: 'success'
                    }),
                    1000
                  )
                } else {
                  this.$message.error(res.data.message)
                }
              })
          } else {
            //此处id不为空，为更新操作
            this.$http
              .post('/classsit/saveOrUpdateClasssit', this.classsitListForm)
              .then(res => {
                console.log('res', res)
                this.load()

                if (res.data.code == 200) {
                  this.$refs[classsitListForm].resetFields()
                  setTimeout(
                    this.$message({
                      message: '操作成功',
                      type: 'success'
                    }),
                    1000
                  )
                } else {
                  this.$message.error(res.data.message)
                }
              })
          }
          this.closeDialog()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // dialog取消
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.closeDialog()
    },
    handleDeleteClick (rows) {
      //删除教室
      console.log('delete rows', rows)
      var id = rows.syhSitId
      this.$confirm('此操作将永久删除该教室工位, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          console.log('postid', id)
          this.$http
            .get('/classsit/deleteClasssit', { params: { classsitId: id } })
            .then(res => {
              console.log('res', res)
              this.load()
              if (res.data.code == 200) {
                //成功删除弹窗
                setTimeout(
                  this.$message({
                    type: 'success',
                    message: '删除成功!'
                  }),
                  1000
                )
              } else {
                this.$message.error(res.data.message)
              }
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    closeDialog () {
      this.classsitListForm = { ...this.classsitListFormInit }
      this.dialogFormVisible = false
    }
  }
}
</script>
<style scoped></style>
