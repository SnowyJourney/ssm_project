<template>
  <div>
    <!--    新增和其他选项-->
    <div style="margin: 7px 5px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>其他</el-button>
    </div>
    <!--    搜索框-->
    <div style="margin: 5px 5px">
      <el-input v-model="search" style="width: 30%"  placeholder="请输入给关键字" />
      <el-button style="margin-left: 3px" type="primary" @click="list">搜索</el-button>
    </div>
    <!--    内容表格-->
    <el-table :data="tableData" stripe style="width: 90%">
      <el-table-column prop="id" label="ID" sortable></el-table-column>
      <el-table-column prop="name" label="名称" sortable></el-table-column>
      <el-table-column prop="maker" label="厂商"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column prop="stock" label="容量"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button link type="primary"  @click="FurnEdit(scope.row)">编辑</el-button>
          <!--            删除弹出确认框-->
          <el-popconfirm title="确认要删除吗?" @confirm="delRow(scope.row.id)">
            <template #reference>
              <el-button>删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    新增提示框输入内容-->
    <el-dialog v-model="dialogVisible" title="提示" width="30%">
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <el-form-item label="家具名字" prop="name">
          <el-input v-model="form.name"/>
<!--          {{validErrors.name}}-->
        </el-form-item>
        <el-form-item label="制造厂商" prop="maker">
          <el-input v-model="form.maker"/>
<!--          {{validErrors.maker}}-->
        </el-form-item>
        <el-form-item label="定制价格" prop="price">
          <el-input v-model="form.price"/>
<!--          {{validErrors.price}}-->
        </el-form-item>
        <el-form-item label="存储容量" prop="stock">
          <el-input v-model="form.stock"/>
<!--          {{validErrors.stock}}-->
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">确定</el-button>
          <el-button @click="dialogVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--    添加分页导航-->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[3,6,9]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";
export default {
  name: 'HomeView',
  components: {
  },
  data(){
    return {
      dialogVisible: false, //默认设置不可见
      form: {},//表单数据
      tableData: [],
      currentPage: 1,
      pageSize: 6,
      total: 0,
      search: '',
      rules: { //表单数据校验规则
        name: [{required: true, message: '请输入家居名', trigger: 'blur'}],
        maker: [{required: true, message: '请输入制造商', trigger: 'blur'}],
        price: [{required: true, message: '请输入价格', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/, message: '请输入数字', trigger: 'blur'}],
        stock: [{required: true, message: '请输入库存', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))$/, message: '请输入数字', trigger: 'blur'}]
      },
      // 这个是接受后端数据校验数据失败返回的错误信息
      validErrors:{}
    }
  },
  created() {
    this.list()
  },
  methods:{
    add(){
      //显示提交表单
      this.dialogVisible = true;
      //清空上次填写表单数据
      this.form ={};
      // 刷新列表
      this.list();
    },
    save(){
      // 执行修改
      if(this.form.id!=null){
          request.put("/Fur/update",this.form).then(
              res =>{
                if(res.code === 100){
                  this.$message({message: '更新成功', type: 'success'});
                  // 更新成功就刷新页面
                  this.dialogVisible = false;
                  this.list();
                }else{
                  this.$message({message: '更新失败',type: 'error'});
                  this.dialogVisible = false;
                }
        })
      }else{ //执行添加操作
        this.$refs['form'].validate(
            valid =>{
              if(valid === true){
                request.post("/Fur/save",this.form).then(
                    res=>{
                      this.dialogVisible = false;
                      this.$message({message:"添加成功",type:'success'})
                      this.list();
                    }
                )
              }else{
                //前端没有验证通过就不提交
                this.$message({type:"error",message:"请填写完整信息"});
                return false;
              }
            }
        )
      }
    },
    // 普通查询
    // list(){
    //   request.get("/Fur/listAll").then(
    //       res=>{
    //         this.tableData=res.data;
    //       }
    //   )
    // 分页查询
    // list(){
    //   request.get("/Fur/listPage",
    //       {params:{pageNum: this.currentPage,pageSize: this.pageSize}}).then(
    //       res => {
    //         this.tableData = res.data.records;
    //
    //         this.total = res.data.total;
    //       }
    //   )
    // }
    // 分页查询+条件
    list(){
      request.get("/Fur/conditionPage",
          {params:{pageNum: this.currentPage,pageSize: this.pageSize,search:this.search}}).then(
          res => {
            this.tableData = res.data.records;

            this.total = res.data.total;
          }
      )
    },
    // 编辑对话框
    FurnEdit(row){
      this.dialogVisible = true;
      this.form = JSON.parse(JSON.stringify(row))
    },
    FurnEdit2(row){
      this.dialogVisible=true;
      request.get("/Fur/findById/"+row.id).then(
          res=>{
            this.form = res.data;
          }
      )
    },
    delRow(id){
      request.delete("/Fur/del/"+id).then(
          res =>{
            if(res.code === 100){
              this.$message({message: '删除成功',type: 'success'});
              this.list();
            }else{
              this.$message({message: "删除失败",type: 'error'});
            }
          }
      )
    },
    handlePageSizeChange(pageSize){
      this.pageSize=pageSize;
      this.list();
    },
    handleCurrentChange(currentPage){
      this.currentPage=currentPage;
      this.list();
    }
  }
}
</script>
