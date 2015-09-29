import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NimbusLogsTests{

    public static final String MACHINE_NAME = "default";
    private static List<NimbusLogs.ConfigurationSetting> configurationSettings;

    @BeforeClass
    public static void setUp() throws Exception {
//        App.main(new String[]{MACHINE_NAME});
        configurationSettings = new NimbusLogs(new Docker(MACHINE_NAME)).toList();
    }

    @Test
    public void nimbusChildOptsIsSet() throws Exception {
        assertThat(getValue("nimbus.childopts"), is("-Xmx1024m"));
    }

    @Test
    public void uiChildOptsIsSet() throws Exception {
        assertThat(getValue("ui.childopts"), is("-Xmx768m"));
    }

    @Test
    public void logviewerChildOptsIsSet() throws Exception {
        assertThat(getValue("logviewer.childopts"), is("-Xmx128m"));
    }

    private String getValue(String setting) {
        return configurationSettings
                .stream()
                .filter(configurationSetting -> configurationSetting.getKey().equals(setting))
                .findFirst()
                .get()
                .getValue();
    }
}
