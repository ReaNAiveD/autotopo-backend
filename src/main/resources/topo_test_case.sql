INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (1, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterA', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (2, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterB', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (3, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterC', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (4, 'show ip route', '([\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*S.*#4/#5.*via #6[\s\S]*)|([\s\S]*S.*#4/#5.*via #6[\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*)', 'RouterA', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (5, 'show ip route', '([\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*)|([\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*)', 'RouterB', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (6, 'show ip route', '([\s\S]*S.*#1/#2.*via #3[\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*)|([\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*S.*#1/#2.*via #3[\s\S]*)', 'RouterC', 0);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (7, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterA', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (8, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterB', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (9, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterC', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (10, 'show ip route', '[\s\S]*R.*#1/#2.*via #3.*(?i)s.*#4[\s\S]*', 'RouterA', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (11, 'show ip route', '([\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*)|([\s\S]*C.*#4/#5 is directly connected, (?i)s.*#6[\s\S]*C.*#1/#2 is directly connected, (?i)s.*#3[\s\S]*)', 'RouterB', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (12, 'show ip route', '[\s\S]*R.*#1/#2.*via #3.*(?i)s.*#4[\s\S]*', 'RouterC', 1);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (13, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (14, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (15, 'ping #1', '^(?![\s\S]*?Success rate is 0 percent \(0/5\))[\s\S]*$', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (16, 'show ip ospf database', '[\s\S]*Router Link States[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (17, 'show ip ospf database', '[\s\S]*Summary Net Link States[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (18, 'show ip ospf database', '[\s\S]*Summary ASB Link States[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (19, 'show ip ospf database', '[\s\S]*Type-5 AS External Link States[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (20, 'show ip ospf database', '[\s\S]*(?<!Summary )Net Link States[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (21, 'show ip ospf database', '[\s\S]*Router Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (22, 'show ip ospf database', '[\s\S]*Summary Net Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (23, 'show ip ospf database', '[\s\S]*(?<!Summary )Net Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (24, 'show ip ospf database', '[\s\S]*Summary ASB Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (25, 'show ip ospf database', '[\s\S]*Type-5 AS External Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (26, 'show ip ospf database', '[\s\S]*Type-7 AS External Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (27, 'show ip ospf database', '[\s\S]*Router Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (28, 'show ip ospf database', '[\s\S]*Summary Net Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (29, 'show ip ospf database', '[\s\S]*Type-7 AS External Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (30, 'show ip route', '([\s\S]*O.*#1/#2.*via #3.*(?i)s.*#4[\s\S]*', 'RouterA', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (31, 'show ip route', '([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5.*via #2.*(?i)s.*#3[\s\S]*O.*#4.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)', 'RouterB', 2)
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (32, 'show ip route', '([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)', 'RouterC', 2);
