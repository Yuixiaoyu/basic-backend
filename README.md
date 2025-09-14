# Basic Backend - 后端基础模板

一个基于Spring Boot 3的现代化后端基础模板，集成了用户管理、权限认证、文件存储和代码生成等核心功能。

## 📋 项目简介

这是一个功能完整的企业级后端服务模板，主要特性包括：
- 🔐 **用户认证与权限管理** - 基于Sa-Token的JWT认证体系
- 📁 **文件存储服务** - 集成MinIO对象存储，支持单文件/批量上传
- 📝 **RESTful API设计** - 标准化的接口设计与异常处理
- 📚 **API文档** - 集成Knife4j，提供完整的接口文档
- 🛠️ **代码生成器** - 基于FreeMarker模板的代码自动生成
- 🔄 **Redis缓存** - 集成Redisson，支持分布式缓存
- 🎯 **AOP切面** - 权限拦截、日志记录等切面功能

## 🛠️ 技术栈

### 核心框架
- **Spring Boot 3.5.4** - 主框架
- **Java 21** - 编程语言
- **Maven** - 依赖管理与构建工具

### 数据存储
- **MySQL 8.0+** - 关系型数据库
- **MyBatis Plus 3.5.12** - ORM框架，增强版MyBatis
- **HikariCP** - 高性能数据库连接池
- **Redis** - 分布式缓存与会话存储
- **Redisson 3.21.0** - Redis Java客户端

### 认证与安全
- **Sa-Token 1.44.0** - 轻量Java权限认证框架
- **JWT** - JSON Web Token认证
- **Spring Boot AOP** - 面向切面编程，用于权限拦截

### 文件存储
- **MinIO 8.5.11** - 高性能对象存储服务

### 工具库
- **Lombok 1.18.36** - 代码简化注解
- **Hutool 5.8.38** - Java工具类库
- **Knife4j 4.4.0** - API文档生成工具
- **FreeMarker** - 模板引擎，用于代码生成

### 测试框架
- **Spring Boot Test** - 集成测试框架

## 🔧 环境要求

### 系统要求
- **JDK 21** 或更高版本
- **Maven 3.6+**
- **MySQL 8.0+**
- **Redis 6.0+** (可选，用于会话管理)
- **MinIO Server** (用于文件存储)

### 开发工具推荐
- **IntelliJ IDEA 2023.3+** 或 **Eclipse 2023-06+**
- **Git** 版本控制
- **Postman** 或 **Apifox** (API测试)
- **Navicat** 或 **DataGrip** (数据库管理)

## 🚀 快速开始

### 1. 环境准备

#### 安装JDK 21
```bash
# 确认Java版本
java -version
# 应该显示 JDK 21.x.x 或更高版本
```

#### 安装MySQL 8.0+
```sql
-- 创建数据库
CREATE DATABASE basic CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 创建用户（可选）
CREATE USER 'basic_user'@'%' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON basic.* TO 'basic_user'@'%';
FLUSH PRIVILEGES;
```

#### 安装Redis (可选)
```bash
# 使用Docker运行Redis
docker run -d --name redis \
  -p 6379:6379 \
  redis:7-alpine redis-server --appendonly yes
```

#### 安装MinIO
```bash
# 使用Docker运行MinIO
docker run -d \
  --name minio \
  -p 9000:9000 -p 9001:9001 \
  -e "MINIO_ROOT_USER=minioadmin" \
  -e "MINIO_ROOT_PASSWORD=minioadmin" \
  -v /data:/data \
  minio/minio server /data --console-address ":9001"
```

MinIO管理控制台访问地址：http://localhost:9001

### 2. 数据库初始化

执行项目中的SQL脚本：
```bash
# 进入项目根目录
cd basic-backend

# 执行建表脚本
mysql -u root -p basic < sql/create_table.sql

# 或者在MySQL客户端中执行
SOURCE /path/to/basic-backend/sql/create_table.sql;
```

数据库表结构说明：
- `user`: 用户表，包含用户基本信息、角色权限等
- 支持逻辑删除、唯一约束等高级特性

### 3. 配置文件

修改 `src/main/resources/application-dev.yml` 或 `application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/basic?useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai
    username: root  # 修改为你的数据库用户名
    password: admin # 修改为你的数据库密码
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      # password: your_redis_password  # 如果设置了Redis密码

# MinIO配置
minio:
  endpoint: http://127.0.0.1:9000
  accessKey: minioadmin      # 修改为你的MinIO用户名
  secretKey: minioadmin      # 修改为你的MinIO密码
  bucket: basic-files        # 修改为你的存储桶名称

# Sa-Token配置
sa-token:
  jwt-secret-key: your-secret-key-here  # 修改为你的JWT密钥
```

**重要提示**：
- 生产环境中请修改默认密码
- JWT密钥应使用强密码
- MinIO需要先创建对应的Bucket

### 4. 构建与运行

```bash
# 1. 克隆项目
git clone <repository-url>
cd basic-backend

# 2. 安装依赖
mvn clean install

# 3. 编译项目
mvn clean compile

# 4. 运行项目
# 方式1：使用Maven插件
mvn spring-boot:run

# 方式2：直接运行主类
java -jar target/basic-backend-0.0.1-SNAPSHOT.jar

# 方式3：在IDE中直接运行BasicBackendApplication主类
```

### 5. 验证安装

启动成功后，可以访问以下地址验证：

- **API文档**: http://localhost:8123/api/doc.html
- **健康检查**: http://localhost:8123/api/health
- **服务基础信息**: http://localhost:8123/api

成功启动后，你将在控制台看到类似以下信息：
```
2025-01-14 10:00:00.000  INFO 12345 --- [           main] c.x.b.BasicBackendApplication            : Started BasicBackendApplication in 3.456 seconds
```

## 📁 项目结构

```
src/main/java/com/xiaoyu/basic/
├── annotation/            # 自定义注解
│   └── AuthCheck.java     # 权限检查注解
├── aop/                   # 切面编程
│   └── AuthInterceptor.java # 权限拦截器
├── common/                # 通用类
│   ├── BaseResponse.java  # 统一响应类
│   ├── DeleteRequest.java # 删除请求类
│   ├── PageRequest.java   # 分页请求类
│   └── ResultUtils.java   # 响应工具类
├── config/                # 配置类
│   ├── CorsConfig.java    # 跨域配置
│   ├── JsonConfig.java    # JSON配置
│   ├── MinioConfig.java   # MinIO配置
│   └── RedissonConfig.java # Redis配置
├── constant/              # 常量定义
│   └── UserConstant.java  # 用户常量
├── controller/            # 控制器层
│   ├── HealthController.java  # 健康棄接口
│   ├── MinioController.java   # 文件上传接口
│   └── UserController.java    # 用户管理接口
├── exception/             # 异常处理
│   ├── BusinessException.java      # 业务异常
│   ├── ErrorCode.java             # 错误码枚举
│   ├── GlobalExceptionHandler.java # 全局异常处理器
│   └── ThrowUtils.java            # 异常工具类
├── generator/             # 代码生成器
│   └── CodeGenerator.java # FreeMarker代码生成
├── mapper/                # 数据访问层
│   └── UserMapper.java    # 用户Mapper接口
├── model/                 # 数据模型
│   ├── dto/              # 数据传输对象
│   │   ├── UserAddRequest.java
│   │   ├── UserLoginRequest.java
│   │   ├── UserQueryRequest.java
│   │   ├── UserRegisterRequest.java
│   │   └── UserUpdateRequest.java
│   ├── entity/           # 实体类
│   │   └── User.java
│   ├── enums/            # 枚举类
│   │   ├── CodeGenTypeEnum.java
│   │   └── UserRoleEnum.java
│   └── vo/               # 视图对象
│       ├── LoginUserVO.java
│       └── UserVO.java
├── satoken/               # Sa-Token配置
│   ├── SaTokenConfigure.java  # Sa-Token主配置
│   └── StpInterfaceImpl.java  # 权限接口实现
├── service/               # 服务层
│   ├── impl/
│   │   ├── MinioService.java      # MinIO服务实现
│   │   └── UserServiceImpl.java   # 用户服务实现
│   └── UserService.java       # 用户服务接口
├── utils/                 # 工具类
│   └── DeviceUtils.java   # 设备工具类
└── BasicBackendApplication.java # 启动类

src/main/resources/
├── mapper/                # MyBatis XML映射文件
│   └── UserMapper.xml
├── templates/             # FreeMarker模板
│   ├── model/            # 实体模板
│   ├── TemplateController.java.ftl
│   ├── TemplateService.java.ftl
│   └── TemplateServiceImpl.java.ftl
├── application.yml        # 主配置文件
└── application-dev.yml    # 开发环境配置

sql/
└── create_table.sql       # 建表SQL脚本
```

## 🔗 API接口

### 用户管理接口
| 方法 | 路径 | 功能 | 说明 |
|------|------|------|------|
| POST | `/api/user/register` | 用户注册 | 新用户注册账号 |
| POST | `/api/user/login` | 用户登录 | 返回Sa-Token |
| GET | `/api/user/get/login` | 获取登录用户信息 | 需要身份认证 |
| POST | `/api/user/logout` | 用户登出 | 清除登录状态 |
| POST | `/api/user/add` | 创建用户 | 仅管理员可用 |
| GET | `/api/user/get` | 获取用户信息 | 仅管理员可用 |
| GET | `/api/user/get/vo` | 获取用户VO | 数据脱敏处理 |
| POST | `/api/user/delete` | 删除用户 | 仅管理员可用 |
| POST | `/api/user/update` | 更新用户信息 | 用户可更新本人信息 |
| POST | `/api/user/list/page/vo` | 分页获取用户列表 | 仅管理员可用 |

### 文件管理接口
| 方法 | 路径 | 功能 | 说明 |
|------|------|------|------|
| POST | `/api/minio/upload` | 单文件上传 | 返回永久访问URL |
| POST | `/api/minio/upload/batch` | 批量文件上传 | 返回URL列表 |
| DELETE | `/api/minio/delete` | 删除单个文件 | 根据文件路径删除 |
| DELETE | `/api/minio/delete/batch` | 批量删除文件 | 批量删除指定文件 |

### 系统接口
| 方法 | 路径 | 功能 | 说明 |
|------|------|------|------|
| GET | `/api/health` | 健康检查 | 系统状态检查 |

## 🎯 核心功能

### 1. 用户认证与权限管理
- **Sa-Token认证体系**：集成轻量级权限认证框架
- **JWT Token**：无状态认证，支持分布式部署
- **角色权限**：支持admin/user角色，可扩展
- **登录状态管理**：支持单点登录、记住我等功能
- **AOP权限拦截**：基于注解的权限控制

### 2. 文件存储服务
- **MinIO对象存储**：高性能、可扩展的对象存储
- **文件上传**：支持单文件和批量上传
- **文件管理**：支持文件删除、查看等操作
- **URL生成**：自动生成永久访问URL

### 3. 数据库操作
- **MyBatis Plus**：强大的ORM框架，简化CRUD操作
- **逻辑删除**：支持软删除，数据安全
- **分页查询**：内置分页插件，简化分页实现
- **条件构造器**：类型SQLQueryWrapper，安全防注入

### 4. Redis缓存
- **Redisson集成**：高性能Redis Java客户端
- **会话存储**：用户登录状态存储
- **分布式锁**：支持分布式系统中的并发控制

### 5. 代码生成器
- **FreeMarker模板**：基于模板的代码生成
- **自动生成**：Controller、Service、DTO、VO等样板代码
- **可定制化**：支持修改生成模板，适应不同需求

### 6. 异常处理与日志
- **全局异常处理**：统一异常处理和响应格式
- **业务异常**：自定义业务异常类型
- **错误码管理**：统一错误码定义和管理
- **请求日志**：详细的请求和响应日志记录

## 🚀 开发指南

### 使用代码生成器

本项目提供了基于FreeMarker的代码生成器，可以快速生成CRUD相关代码。

1. **修改生成参数**
编辑 `CodeGenerator.java` 中的参数：
```java
String packageName = "com.xiaoyu.basic";
String dataName = "题目";  // 中文名称
String dataKey = "question";   // 驼峰命名
String upperDataKey = "Question"; // 大驼峰命名
```

2. **运行生成器**
```bash
# 在IDE中直接运行CodeGenerator主方法
# 或者使用Maven运行
mvn exec:java -Dexec.mainClass="com.xiaoyu.basic.generator.CodeGenerator"
```

3. **生成的文件**
生成器将创建以下文件：
- Controller：`{Entity}Controller.java`
- Service接口：`{Entity}Service.java`
- Service实现：`{Entity}ServiceImpl.java`
- DTO类：`{Entity}AddRequest.java`、`{Entity}QueryRequest.java` 等
- VO类：`{Entity}VO.java`

### 添加新实体步骤

1. **创建数据库表**
2. **生成实体类**（使用MyBatis Plus生成器或手动创建）
3. **使用代码生成器**生成相关代码
4. **添加Mapper XML**（如果需要复杂查询）
5. **注册到主类**（使用@MapperScan）

### 部署指南

#### Docker部署

1. **构建镜像**
```dockerfile
# Dockerfile
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/basic-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8123
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# 构建和运行
mvn clean package -DskipTests
docker build -t basic-backend .
docker run -p 8123:8123 basic-backend
```

2. **Docker Compose部署**
```yaml
# docker-compose.yml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8123:8123"
    depends_on:
      - mysql
      - redis
      - minio
    environment:
      - SPRING_PROFILES_ACTIVE=prod
  
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: basic
    ports:
      - "3306:3306"
  
  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
  
  minio:
    image: minio/minio
    ports:
      - "9000:9000"
      - "9001:9001"
    environment:
      MINIO_ROOT_USER: minioadmin
      MINIO_ROOT_PASSWORD: minioadmin
    command: server /data --console-address ":9001"
```

#### 生产环境部署

1. **修改配置文件**：创建 `application-prod.yml`
2. **数据库迁移**：使用Flyway或Liquibase
3. **监控部署**：集成Spring Boot Actuator
4. **日志配置**：配置Logback日志输出

## 📝 常见问题

### Q: 启动时报错 "Access denied for user..."
A: 检查数据库连接配置，确保用户名密码正确，数据库已创建。

### Q: MinIO连接失败
A: 确保 MinIO 服务正在运行，检查 endpoint、accessKey、secretKey 配置。

### Q: Redis连接失败
A: 检查Redis服务状态，确认端口和连接配置正确。

### Q: 如何修改默认端口？
A: 在 `application.yml` 中修改 `server.port` 配置。

### Q: 如何添加新的权限角色？
A: 在 `UserRoleEnum` 中添加新角色，在 `StpInterfaceImpl` 中实现权限逻辑。

## 🎤 更新日志

### v0.0.1 (2025-01-14)
- ✅ 初始版本发布
- ✅ 集成Spring Boot 3.5.4 + Java 21
- ✅ 实现用户认证与权限管理
- ✅ 集成MinIO文件存储服务
- ✅ 集成Sa-Token权限认证框架
- ✅ 实现代码生成器
- ✅ 集成Knife4j API文档

## 🤝 贡献指南

欢迎各种形式的贡献！

1. **反馈问题**：在Issues中提出问题或建议
2. **代码贡献**：
   - Fork 项目
   - 创建功能分支 (`git checkout -b feature/AmazingFeature`)
   - 提交更改 (`git commit -m 'Add some AmazingFeature'`)
   - 推送到分支 (`git push origin feature/AmazingFeature`)
   - 开启 Pull Request
3. **文档完善**：帮助完善项目文档
4. **功能扩展**：添加新特性或优化现有功能

## 📄 许可证

本项目采用 [MIT](LICENSE) 许可证。

## 📞 联系方式
如有问题或建议，请通过以下方式联系：

<img style="width:200px;height:280px" src="https://youke1.picui.cn/s1/2025/09/14/68c5a03405338.jpg"></img>

