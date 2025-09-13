# Basic Backend - 后端基础模板

一个基于Spring Boot 3的后端基础模板，集成了用户管理、用户登录、文件存储功能。

## 📋 项目简介

这是一个现代化的后端服务项目，主要功能包括：
- 🔐 用户认证与权限管理
- 📁 文件存储服务（基于MinIO）
- 📝 RESTful API设计
- 📚 完整的API文档（Knife4j）

## 🛠️ 技术栈

### 核心框架
- **Spring Boot 3.5.4** - 主框架
- **Java 21** - 编程语言
- **Maven** - 依赖管理与构建工具

### 数据库相关
- **MySQL** - 关系型数据库
- **MyBatis Plus 3.5.12** - ORM框架
- **HikariCP** - 数据库连接池

### 文件存储
- **MinIO 8.5.11** - 对象存储服务

### 工具库
- **Lombok 1.18.36** - 代码简化
- **Hutool 5.8.38** - Java工具类库
- **Knife4j 4.4.0** - API文档工具

### 其他
- **Spring Boot AOP** - 面向切面编程
- **Spring Boot Test** - 测试框架

## 🔧 环境要求

### 系统要求
- **JDK 21** 或更高版本
- **Maven 3.6+**
- **MySQL 8.0+**
- **MinIO Server**

### 开发工具推荐
- IntelliJ IDEA 2023.3+
- Git
- Postman（API测试）

## 🚀 快速开始

### 1. 环境准备

#### 安装JDK 21
```bash
# 确认Java版本
java -version
```

#### 安装MySQL
- 下载并安装MySQL 8.0+
- 创建数据库：`basic`

#### 安装MinIO
```bash
# 使用Docker运行MinIO
docker run -p 9000:9000 -p 9001:9001 \
  --name minio \
  -e "MINIO_ROOT_USER=admin" \
  -e "MINIO_ROOT_PASSWORD=admin" \
  minio/minio server /data --console-address ":9001"
```

### 2. 数据库初始化

执行项目中的SQL脚本：
```sql
-- 运行 sql/create_table.sql 中的建表语句
```

### 3. 配置文件

修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/basic?useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai
    username: your_mysql_username
    password: your_mysql_password

minio:
  endpoint: http://127.0.0.1:9000
  accessKey: admin
  secretKey: admin
  bucket: your-bucket-name

```

### 4. 构建与运行

```bash
# 克隆项目
git clone <repository-url>
cd basic-backend

# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

### 5. 验证安装

- 访问API文档：http://localhost:8123/api/doc.html
- 健康检查：http://localhost:8123/api/health

## 📁 项目结构

```
src/main/java/com/xiaoyu/basic/
├── annotation/            # 自定义注解
├── aop/                   # 切面编程
├── common/                # 通用类
├── config/                # 配置类
├── constant/              # 常量定义
├── controller/            # 控制器层
├── exception/             # 异常处理
├── mapper/                # 数据访问层
├── model/                 # 数据模型
│   ├── dto/              # 数据传输对象
│   ├── entity/           # 实体类
│   ├── enums/            # 枚举类
│   └── vo/               # 视图对象
├── service/               # 服务层
└── BasicBackendApplication.java
```

## 🔗 API接口

### 用户管理
- `POST /api/user/register` - 用户注册
- `POST /api/user/login` - 用户登录
- `GET /api/user/get/login` - 获取登录用户信息
- `POST /api/user/logout` - 用户登出

### 文件管理
- `POST /api/minio/upload` - 单文件上传
- `POST /api/minio/upload/batch` - 批量文件上传
- `DELETE /api/minio/delete` - 删除文件

### 系统
- `GET /api/health` - 健康检查

## 🎯 核心功能

### 1. 用户权限管理
- JWT身份认证
- 基于角色的权限控制
- 登录状态管理

### 2. 文件存储
- MinIO对象存储
- 文件上传/下载
- 批量文件操作

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 [MIT](LICENSE) 许可证。

## 📞 联系方式
如有问题或建议，请通过以下方式联系：

<img style="width:200px;height:280px" src="https://youke1.picui.cn/s1/2025/09/14/68c5a03405338.jpg"></img>

