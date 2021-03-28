<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="目录名称" prop="catalogName">
        <el-input
          v-model="queryParams.catalogName"
          placeholder="请输入目录名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属行业" prop="tradeId">
        <el-select v-model="queryParams.tradeId" placeholder="请选择所属行业" clearable size="small">
          <el-option
            v-for="trade in tradeOptions"
            :key="trade.id"
            :label="trade.name"
            :value="trade.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="父级目录" prop="parentId">
        <el-select v-model="queryParams.parentId" placeholder="请选择父级目录" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="排序" prop="showOrder">
        <el-input
          v-model="queryParams.showOrder"
          placeholder="请输入排序"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目录层级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入目录层级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目录资源数" prop="resourceTotal">
        <el-input
          v-model="queryParams.resourceTotal"
          placeholder="请输入目录资源数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="删除时间" prop="deletedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.deletedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择删除时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="删除人" prop="deletedBy">
        <el-input
          v-model="queryParams.deletedBy"
          placeholder="请输入删除人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除标记" prop="deletedFlag">
        <el-input
          v-model="queryParams.deletedFlag"
          placeholder="请输入删除标记"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
	    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basedata:resourceCatalog:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="resourceCatalogList"
      row-key="id"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="目录名称" align="center" prop="catalogName" />
      <el-table-column label="所属行业" align="center" prop="tradeId" />
      <el-table-column label="父级目录" align="center" prop="parentId" />
      <el-table-column label="所有父级" align="center" prop="parentIds" />
      <el-table-column label="排序" align="center" prop="showOrder" />
      <el-table-column label="目录层级" align="center" prop="level" />
      <el-table-column label="目录资源数" align="center" prop="resourceTotal" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除时间" align="center" prop="deletedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deletedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除人" align="center" prop="deletedBy" />
      <el-table-column label="删除标记" align="center" prop="deletedFlag" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:resourceCatalog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:resourceCatalog:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改资源目录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="目录名称" prop="catalogName">
          <el-input v-model="form.catalogName" placeholder="请输入目录名称" />
        </el-form-item>
        <el-form-item label="所属行业" prop="tradeId">
          <el-select v-model="form.tradeId" placeholder="请选择所属行业">
            <el-option
              v-for="trade in tradeOptions"
              :key="trade.id"
              :label="trade.name"
              :value="trade.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="父级目录" prop="parentId">
          <treeselect v-model="form.parentId" :options="resourceCatalogOptions" :normalizer="normalizer" placeholder="请选择父级目录" />
        </el-form-item>
        <el-form-item label="排序" prop="showOrder">
          <el-input v-model.number="form.showOrder" placeholder="请输入排序" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {tradeList, listResourceCatalog, getResourceCatalog, delResourceCatalog, addResourceCatalog, updateResourceCatalog, exportResourceCatalog } from "@/api/basedata/resourceCatalog";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "ResourceCatalog",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 资源目录表格数据
      resourceCatalogList: [],
      // 资源目录树选项
      resourceCatalogOptions: [],
        //行业下拉
        tradeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        catalogName: null,
        tradeId: null,
        parentId: null,
        parentIds: null,
        showOrder: null,
        level: null,
        resourceTotal: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deletedTime: null,
        deletedBy: null,
        deletedFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
        // 表单校验
       rules: {
           catalogName: [
                { required: true, message: "目录名称不能为空", trigger: "blur" }
            ],
           tradeId:[
               {
                   required: true, message: "所属行业不能为空", trigger: "blur"
               }
           ],
          showOrder:[
              {   type: "number",required: true, message: "请输入正确的排序值", trigger: "blur" },
          ],
           parentId:[
               {
                   required: true, message: "父级目录不能为空", trigger: "blur"
               }
           ]
        }
    };
  },
  created() {
    this.getList();
  },
  methods: {
      /* 查询行业下拉列表*/
      getTradeList(){
          tradeList().then(response => {
              this.tradeOptions = response.data;
          });
      },
    /** 查询资源目录列表 */
    getList() {
      this.loading = true;
      listResourceCatalog(this.queryParams).then(response => {
        this.resourceCatalogList = this.handleTreeUUID(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换资源目录数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.catalogName,
        children: node.children
      };
    },
	/** 查询部门下拉树结构 */
    getTreeselect() {
      listResourceCatalog().then(response => {
        this.resourceCatalogOptions = [];
        const data = { id: 0, catalogName: '顶级节点', children: [] };
        data.children = this.handleTreeUUID(response.data, "id", "parentId");
        this.resourceCatalogOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        catalogName: null,
        tradeId: null,
        parentId: null,
        parentIds: [],
        showOrder: null,
        level: null,
        resourceTotal: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deletedTime: null,
        deletedBy: null,
        deletedFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
	    this.getTreeselect();
	    this.getTradeList();
      this.open = true;
      this.title = "添加资源目录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
	  this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.id;
      }
      getResourceCatalog(row.id).then(response => {
        this.form = response.data;
        this.form.parentIds = this.form.parentIds.split(",");
        this.open = true;
        this.title = "修改资源目录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            console.log(this.form.parentIds);
          this.form.parentIds = this.form.parentIds.join(",");
            console.log(this.form.parentIds);
          if (this.form.id != null) {
            updateResourceCatalog(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResourceCatalog(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除资源目录编号为"' + row.id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delResourceCatalog(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    }
  }
};
</script>
