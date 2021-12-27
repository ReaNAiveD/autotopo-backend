package cn.svecri.autotopo.vo;

import cn.svecri.autotopo.util.TelnetClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TelnetCommand {
    private TelnetClient client;
    private List<String> cmd;
}
