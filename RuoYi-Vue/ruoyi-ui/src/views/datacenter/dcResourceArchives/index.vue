<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-tree
            :data="resourceCatalogOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basedata:databaseServer:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basedata:databaseServer:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basedata:databaseServer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:databaseServer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>

    <el-table v-loading="loading" :data="databaseServerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="服务器名称" align="center" prop="name" />
      <el-table-column label="服务器地址" align="center" prop="host" />
      <el-table-column label="实例名称" align="center" prop="instanceName" />
      <el-table-column label="端口号" align="center" prop="port" />
      <el-table-column label="用户名称" align="center" prop="username" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="数据库名称" align="center" prop="databaseName" />
      <el-table-column label="默认服务器" align="center" prop="isDefault" :formatter="isDefaultFormat" />
      <el-table-column label="所属行业" align="center" prop="tradeId" />
      <el-table-column label="备注" align="center" prop="description" />
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
            v-hasPermi="['basedata:databaseServer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:databaseServer:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      </el-col>
    </el-row>

    <!-- 添加或修改数据库服务器对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务器名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入服务器名称" />
        </el-form-item>
        <el-form-item label="服务器地址" prop="host">
          <el-input v-model="form.host" placeholder="请输入服务器地址" />
        </el-form-item>
        <el-form-item label="实例名称" prop="instanceName">
          <el-input v-model="form.instanceName" placeholder="请输入实例名称" />
        </el-form-item>
        <el-form-item label="端口号" prop="port">
          <el-input v-model="form.port" placeholder="请输入端口号" />
        </el-form-item>
        <el-form-item label="用户名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="数据库名称" prop="databaseName">
          <el-input v-model="form.databaseName" placeholder="请输入数据库名称" />
        </el-form-item>
        <el-form-item label="默认服务器">
          <el-radio-group v-model="form.isDefault">
            <el-radio
              v-for="dict in isDefaultOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="所属行业" prop="tradeId">
          <el-select v-model="form.tradeId" placeholder="请选择所属行业">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="description">
          <el-input v-model="form.description" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable size="small"
                          v-model="form.createdTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人" prop="updatedBy">
          <el-input v-model="form.updatedBy" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable size="small"
                          v-model="form.updatedTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除时间" prop="deletedTime">
          <el-date-picker clearable size="small"
                          v-model="form.deletedTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择删除时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除人" prop="deletedBy">
          <el-input v-model="form.deletedBy" placeholder="请输入删除人" />
        </el-form-item>
        <el-form-item label="删除标记" prop="deletedFlag">
          <el-input v-model="form.deletedFlag" placeholder="请输入删除标记" />
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
import { listDatabaseServer, getDatabaseServer, delDatabaseServer, addDatabaseServer, updateDatabaseServer, exportDatabaseServer } from "@/api/basedata/databaseServer";
import { listResourceCatalog, getResourceCatalog, delResourceCatalog, addResourceCatalog, updateResourceCatalog, exportResourceCatalog, treeselect } from "@/api/basedata/resourceCatalog";
import { listDcResourceArchives, getDcResourceArchives, delDcResourceArchives, addDcResourceArchives, updateDcResourceArchives, exportDcResourceArchives } from "@/api/datacenter/dcResourceArchives";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "DatabaseServer",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 数据库服务器表格数据
      databaseServerList: [],
      //资源目录树选项
      resourceCatalogOptions: undefined,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 默认服务器字典
      isDefaultOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        host: null,
        instanceName: null,
        port: null,
        username: null,
        password: null,
        databaseName: null,
        isDefault: null,
        tradeId: null,
        description: null,
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
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getTreeselect();
    this.getDicts("sys_yes_no").then(response => {
      this.isDefaultOptions = response.data;
    });
  },
  methods: {
    /** 查询数据库服务器列表 */
    getList() {
      this.loading = true;
      listDatabaseServer(this.queryParams).then(response => {
        this.databaseServerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询资源目录树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.resourceCatalogOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    // 默认服务器字典翻译
    isDefaultFormat(row, column) {
      return this.selectDictLabel(this.isDefaultOptions, row.isDefault);
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
        name: null,
        host: null,
        instanceName: null,
        port: null,
        username: null,
        password: null,
        databaseName: null,
        isDefault: "0",
        tradeId: null,
        description: null,
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
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加数据库服务器";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDatabaseServer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改数据库服务器";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDatabaseServer(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDatabaseServer(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除数据库服务器编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delDatabaseServer(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据库服务器数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return exportDatabaseServer(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
};
</script>
