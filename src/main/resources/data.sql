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