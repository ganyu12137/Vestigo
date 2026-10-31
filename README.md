# Vestigo

> 自托管的个人数字痕迹检索器。跨设备、跨工具，一个搜索框找回你记得关键词但忘了在哪的东西。

Vestigo 来自拉丁语 vestigare（追踪），和 vestige（痕迹）同根。

![status](https://img.shields.io/badge/status-planning-yellow)
![java](https://img.shields.io/badge/Java-17-blue)
![springboot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![mysql](https://img.shields.io/badge/MySQL-8-orange)
![license](https://img.shields.io/badge/license-MIT-lightgrey)

> ⚠️ 规划阶段，还没有代码。表结构和接口都会变。

---

## 问题

你记得关键词，但不记得在哪。

- 上周看过的网页，哪台设备、哪个浏览器？
- 跑过的命令，哪个终端？
- 改过的文件，哪个路径？
- 手机存的链接，微信收藏还是备忘录？

每个工具各存各的。找一个东西得挨个搜一遍，还不一定搜全。

## Vestigo 做什么

一个搜索框，所有痕迹按时间排在一起。

- 电脑端被动采集：shell history、文件修改、浏览器历史、git 提交，不用手动录
- 手机端一键上报：iOS 快捷指令 / Android Tasker 分享链接或文本
- 所有来源归一化成同一个事件模型。加新数据源就是加一个采集器，不动表结构
- 数据存在你自己的服务器上，不经过第三方

## 它不是什么

- 不是日记、笔记、待办
- 不是云同步
- 不做全盘文件内容搜索，那是 Everything 的事
- 不做多用户，不做高可用，不是生产级产品
- 完全不记得关键词的情况帮不上忙

---

## 架构

```text
┌─────────────┐   ┌─────────────┐
│  电脑采集器  │   │   手机入口   │
│ shell/文件/  │   │  分享/剪贴板 │
│ 浏览器/git   │   │   快捷指令   │
└──────┬──────┘   └──────┬──────┘
       │                 │
       └────────┬────────┘
                ▼
       ┌─────────────────┐
       │  Spring Boot    │
       │  入库 API        │
       │  搜索 API        │
       │  Token 鉴权      │
       └────────┬────────┘
                ▼
       ┌─────────────────┐
       │     MySQL 8     │
       │  trace_event 表  │
       └────────┬────────┘
                ▼
       ┌─────────────────┐
       │  搜索页面(PWA)   │
       └─────────────────┘
```

采集端只负责上报，服务器汇总，搜索页读。哪台设备没开机都不影响。

技术栈

层 技术
后端 Spring Boot 3.x、Spring Data JPA、Validation、Scheduler
数据库 MySQL 8（ngram 全文索引）
前端 第一版原生 HTML + JS，以后可能换 Vue / PWA
手机端 iOS 快捷指令 / Android Tasker
部署 云服务器，之后加 Docker

---

快速开始

规划阶段，暂时没东西能跑。阶段 0 完成后补全这一节。

计划中的启动方式：

```bash
git clone https://github.com/<your-name>/vestigo.git
mysql -u root -p -e "CREATE DATABASE trace_db DEFAULT CHARACTER SET utf8mb4;"
# 编辑 src/main/resources/application.yml
mvn spring-boot:run
curl http://localhost:8080/api/ping
```

路线图

阶段 内容 状态
0 项目骨架、/api/ping ⬜
1 建表、手动入库、搜索 API、极简页面 ⬜
2 Shell history 采集器 ⬜
3 文件变更采集器 ⬜
4 浏览器历史采集器 ⬜
5 手机分享入口 ⬜
6 搜索质量：降噪、相关性排序、统计页 ⬜

---

几个取舍

· 自托管，但 v1 不做端到端加密。 数据进你自己的服务器，靠 HTTPS 和 Token 鉴权保护。服务器被攻破数据就暴露。E2E 以后再说。
· 用 MySQL 全文索引，不上专用搜索引擎。 个人量级十年也就 1 GB 左右，够用。
· 轮询，不用文件系统监听。 简单，可移植，一分钟延迟能接受。
· iOS 端只能半自动。 沙箱限制，绕不过去。Android 可以全自动，但要额外开发。
· 单用户单节点。 就这么设计的。

相关项目

这个想法不新，只是还没人做成小的自托管工具：

· Memex（Vannevar Bush, 1945）—— 个人联想式记忆库的最初构想
· Microsoft Recall —— 目标一样，但绑云端，隐私争议大
· Rewind.ai —— 录屏方案，商业产品，先做 macOS
· Everything —— 搜文件极快，但只搜文件名

Vestigo 的差别：跨来源、跨设备、自托管、不用手动录。

参与

个人项目


