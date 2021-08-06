package com.tomtom;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import net.sf.launch4j.Builder;
import net.sf.launch4j.BuilderException;
import net.sf.launch4j.Log;
import net.sf.launch4j.Util;
import net.sf.launch4j.config.ConfigPersister;
import net.sf.launch4j.config.ConfigPersisterException;
import net.sf.launch4j.formimpl.MainFrame;

public class ApplicationRelocated {
    public static void main(String[] args) throws IOException, ConfigPersisterException, BuilderException {
        File workdir = new File(args[1]);

        for (File bin : new File(workdir, "bin").listFiles()) {
            bin.setExecutable(true);
        }

        Properties props = Util.getProperties();

        if (args.length == 0) {
            ConfigPersister.getInstance().createBlank();
            MainFrame.createInstance();
            return;
        }

        ConfigPersister.getInstance().load(new File(args[0]));
        Builder b = new Builder(Log.getConsoleLog(), workdir);
        b.build();
    }
}
