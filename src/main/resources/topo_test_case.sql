/*
 Navicat Premium Data Transfer

 Source Server         : topo
 Source Server Type    : SQLite
 Source Server Version : 3030001
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3030001
 File Encoding         : 65001

 Date: 23/12/2021 20:33:20
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for topo_test_case
-- ----------------------------
DROP TABLE IF EXISTS "topo_test_case";
CREATE TABLE "topo_test_case" (
  "case_id" integer,
  "cmd" VARCHAR(64),
  "expected_re" TEXT,
  "target_telnet" TEXT,
  "topo_id" integer,
  PRIMARY KEY ("case_id")
);

-- ----------------------------
-- Records of topo_test_case
-- ----------------------------
INSERT INTO "topo_test_case" VALUES (1, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerA', 0);
INSERT INTO "topo_test_case" VALUES (2, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerB', 0);
INSERT INTO "topo_test_case" VALUES (3, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerC', 0);
INSERT INTO "topo_test_case" VALUES (4, 'show ip route', '([\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\\s\\S]*S.*#4/#5.*via #6)|([\\s\\S]*S.*#4/#5.*via #6[\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3)', 'routerA', 0);
INSERT INTO "topo_test_case" VALUES (5, 'show ip route', '([\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6)|([\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3)', 'routerB', 0);
INSERT INTO "topo_test_case" VALUES (6, 'show ip route', '([\\s\\S]*S.*#1/#2.*via #3[\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6)|([\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\\s\\S]*S.*#1/#2.*via #3)', 'routerC', 0);
INSERT INTO "topo_test_case" VALUES (7, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerA', 1);
INSERT INTO "topo_test_case" VALUES (8, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerB', 1);
INSERT INTO "topo_test_case" VALUES (9, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'routerC', 1);
INSERT INTO "topo_test_case" VALUES (10, 'show ip route', '[\\s\\S]*R.*#1/#2.*via #3.*(?i)s.*#4[\\s\\S]*', 'routerA', 1);
INSERT INTO "topo_test_case" VALUES (11, 'show ip route', '([\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6)|([\\s\\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\\s\\S]*C.*#1/#2 is directly connected, (?i)s.*#3)', 'routerB', 1);
INSERT INTO "topo_test_case" VALUES (12, 'show ip route', '[\\s\\S]*R.*#1/#2.*via #3.*(?i)s.*#4[\\s\\S]*', 'routerC', 1);

-- ----------------------------
-- Indexes structure for table topo_test_case
-- ----------------------------
CREATE INDEX "IDXajpna59e37lfwd0k64cseptbg"
ON "topo_test_case" (
  "topo_id" ASC
);

PRAGMA foreign_keys = true;
