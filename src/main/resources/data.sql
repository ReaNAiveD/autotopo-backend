INSERT INTO topo_info (topo_id, config_schema, name) VALUES (0, 'test config schema', 'static'), (1, 'test config schema', 'rip'), (2, 'test config schema', 'ospf');
-- ----------------------------
-- Records of topo_conf
-- ----------------------------
INSERT INTO "topo_conf" VALUES (0, '{
	"topotype": "static",
	"router": [
		{
			"name": "RouterA",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"ip route 192.168.30.0 255.255.255.0 192.168.20.2"
			]
		},
		{
			"name": "RouterB",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "192.168.30.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": []
		},
		{
			"name": "RouterC",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.30.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.3",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"ip route 192.168.20.0 255.255.255.0 192.168.30.1"
			]
		}
	],
	"line": [
		{
			"from": [
				"RouterA",
				"s0/0/0"
			],
			"to": [
				"RouterB",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterB",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterA",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/1"
			],
			"logicalConnected": false
		}
	]
}', 1, 0, '2021-12-22 07:15:29', 0);
INSERT INTO "topo_conf" VALUES (1, '{
	"topotype": "rip",
	"router": [
		{
			"name": "RouterA",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"router rip",
				"network 192.168.10.0",
				"network 192.168.20.0"
			]
		},
		{
			"name": "RouterB",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "192.168.30.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"router rip",
				"network 192.168.20.0",
				"network 192.168.30.0"
			]
		},
		{
			"name": "RouterC",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.30.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.3",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"router rip",
				"network 192.168.30.0",
				"network 192.168.10.0"
			]
		}
	],
	"line": [
		{
			"from": [
				"RouterA",
				"s0/0/0"
			],
			"to": [
				"RouterB",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterB",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterA",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/1"
			],
			"logicalConnected": false
		}
	]
}', 1, 0, '2021-12-22 07:15:29', 1);
INSERT INTO "topo_conf" VALUES (2, '{
	"topotype": "ospf",
	"router": [
		{
			"name": "RouterA",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "lo0",
					"ip": "172.16.1.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "lo1",
					"ip": "172.16.2.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "lo2",
					"ip": "172.16.3.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "192.168.40.2",
					"mask": 24,
					"up": true
				}
			],
			"command": [
				"router ospf 1",
				"network 192.168.20.0 0.0.0.255 area 0",
				"network 172.16.0.0 0.0.255.255 area 1",
				"network 192.168.40.0 0.0.0.255 area 0"
			]
		},
		{
			"name": "RouterB",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.20.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "192.168.30.1",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "192.168.40.1",
					"mask": 24,
					"up": true
				}
			],
			"command": [
				"router ospf 1",
				"network 192.168.20.0 0.0.0.255 area 0",
				"network 192.168.30.0 0.0.0.255 area 51",
				"network 192.168.40.0 0.0.0.255 area 0",
				"area 51 nssa"
			]
		},
		{
			"name": "RouterC",
			"port": [
				{
					"name": "s0/0/0",
					"ip": "192.168.30.2",
					"mask": 24,
					"up": true
				},
				{
					"name": "s0/0/1",
					"ip": "",
					"mask": 0,
					"up": false
				},
				{
					"name": "f0/0",
					"ip": "192.168.10.3",
					"mask": 24,
					"up": true
				},
				{
					"name": "f0/1",
					"ip": "",
					"mask": 0,
					"up": false
				}
			],
			"command": [
				"ip route 199.9.9.0 255.255.255.0 null0",
				"router ospf 1",
				"network 192.168.30.0 0.0.0.255 area 51",
				"redistribute static metric-type 1",
				"area 51 nssa"
			]
		}
	],
	"switch": {
		"name": "SwitchA"
	},
	"line": [
		{
			"from": [
				"RouterA",
				"s0/0/0"
			],
			"to": [
				"RouterB",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterB",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterA",
				"f0/1"
			],
			"to": [
				"SwitchA",
				"f0/0"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"SwitchA",
				"f0/1"
			],
			"to": [
				"RouterB",
				"f0/1"
			],
			"logicalConnected": true
		},
		{
			"from": [
				"RouterA",
				"s0/0/1"
			],
			"to": [
				"RouterC",
				"s0/0/1"
			],
			"logicalConnected": false
		},
		{
			"from": [
				"SwitchA",
				"f0/2"
			],
			"to": [
				"RouterC",
				"f0/1"
			],
			"logicalConnected": false
		}
	]
}', 1, 0, '2021-12-22 07:15:29', 2);


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
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (24, 'show ip ospf database', '[\s\S]*Type-5 AS External Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (25, 'show ip ospf database', '[\s\S]*Type-7 AS External Link States[\s\S]*', 'RouterB', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (26, 'show ip ospf database', '[\s\S]*Router Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (27, 'show ip ospf database', '[\s\S]*Summary Net Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (28, 'show ip ospf database', '[\s\S]*Type-7 AS External Link States[\s\S]*', 'RouterC', 2);
INSERT INTO "topo_test_case"("case_id", "cmd", "expected_re", "target_telnet", "topo_id") VALUES (29, 'show ip route', '([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*)|([\s\S]*O.*#5/#7.*via #2.*(?i)s.*#3[\s\S]*O.*#4/#6.*via #2.*(?i)s.*#3[\s\S]*O.*#1.*via #2.*(?i)s.*#3[\s\S]*)', 'RouterC', 2);
