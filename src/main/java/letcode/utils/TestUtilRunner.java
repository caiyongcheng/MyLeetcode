package letcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * IDEA 插件运行入口：插件传入当前题解类名后，由这里统一交给 TestUtil 执行。
 */
public class TestUtilRunner {

    private static final Pattern ANSI_ESCAPE = Pattern.compile(
            "\u001B\\[[0-9;?]*[ -/]*[@-~]|\u001B[@-Z\\\\-_]|\u001B\\].*?(?:\u0007|\u001B\\\\)|\u009B[0-9;?]*[ -/]*[@-~]"
    );

    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0] == null || args[0].trim().isEmpty()) {
            throw new IllegalArgumentException("target class name args[0] is required");
        }
        ParsedArgs parsed = parseArgs(args);
        Runnable task = () -> runTest(parsed.className, parsed.input);
        if (parsed.outputMode == OutputMode.CONSOLE) {
            task.run();
            return;
        }
        runWithCapturedOutput(task, parsed.outputMode, parsed.outputFile);
    }

    private static void runTest(String className, String input) {
        try {
            Class<?> targetClass = Class.forName(className);
            if (input == null || input.isEmpty()) {
                TestUtil.test(targetClass);
            } else {
                TestUtil.test(targetClass, input);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("target class not found: " + className, e);
        }
    }

    private static void runWithCapturedOutput(Runnable task, OutputMode mode, Path outputFile) {
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream capture = newUtf8PrintStream(buffer);
        PrintStream out = mode == OutputMode.BOTH ? tee(originalOut, capture) : capture;
        PrintStream err = mode == OutputMode.BOTH ? tee(originalErr, capture) : capture;
        System.setOut(out);
        System.setErr(err);
        try {
            task.run();
        } catch (Throwable t) {
            t.printStackTrace(err);
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof Error) {
                throw (Error) t;
            }
            throw new RuntimeException(t);
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
            writeCapturedOutput(outputFile, buffer);
        }
    }

    private static void writeCapturedOutput(Path outputFile, ByteArrayOutputStream buffer) {
        if (outputFile == null) {
            return;
        }
        try {
            Path parent = outputFile.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.write(outputFile, stripAnsi(buffer.toByteArray()));
        } catch (IOException ignored) {
            // 输出文件写入失败时不阻断进程退出码。
        }
    }

    /** 写入插件输出文件前剥离 ANSI，避免弹窗出现转义码。 */
    private static byte[] stripAnsi(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return bytes == null ? new byte[0] : bytes;
        }
        String text = new String(bytes, StandardCharsets.UTF_8);
        return ANSI_ESCAPE.matcher(text).replaceAll("").getBytes(StandardCharsets.UTF_8);
    }

    private static PrintStream tee(PrintStream primary, PrintStream secondary) {
        return newUtf8PrintStream(new TeeOutputStream(primary, secondary));
    }

    private static PrintStream newUtf8PrintStream(OutputStream out) {
        try {
            return new PrintStream(out, true, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 must be supported", e);
        }
    }

    private static ParsedArgs parseArgs(String[] args) {
        ParsedArgs parsed = new ParsedArgs();
        parsed.className = args[0].trim();
        int i = 1;
        while (i < args.length) {
            String token = args[i];
            if ("--base64".equals(token)) {
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("--base64 requires a value");
                }
                parsed.input = decodeBase64(args[++i]);
                i++;
                continue;
            }
            if ("--output-mode".equals(token)) {
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("--output-mode requires a value");
                }
                parsed.outputMode = OutputMode.fromCli(args[++i]);
                i++;
                continue;
            }
            if ("--output-file".equals(token)) {
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("--output-file requires a value");
                }
                parsed.outputFile = Paths.get(args[++i]);
                i++;
                continue;
            }
            if (token.startsWith("--")) {
                throw new IllegalArgumentException("unknown option: " + token);
            }
            if (parsed.input != null) {
                throw new IllegalArgumentException("unexpected extra argument: " + token);
            }
            // 兼容旧 CLI：第二个位置参数直接作为测试输入。
            parsed.input = token;
            i++;
        }
        return parsed;
    }

    private static String decodeBase64(String encoded) {
        byte[] bytes = Base64.getDecoder().decode(encoded);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private enum OutputMode {
        CONSOLE("console"),
        PLUGIN("plugin"),
        BOTH("both");

        private final String cliValue;

        OutputMode(String cliValue) {
            this.cliValue = cliValue;
        }

        static OutputMode fromCli(String value) {
            if (value == null) {
                throw new IllegalArgumentException("output mode is required");
            }
            String normalized = value.trim().toLowerCase();
            for (OutputMode mode : values()) {
                if (mode.cliValue.equals(normalized)) {
                    return mode;
                }
            }
            throw new IllegalArgumentException("unsupported output mode: " + value);
        }
    }

    private static final class ParsedArgs {
        String className;
        String input;
        OutputMode outputMode = OutputMode.CONSOLE;
        Path outputFile;
    }

    /** 同时写入控制台与捕获缓冲。 */
    private static final class TeeOutputStream extends OutputStream {
        private final PrintStream primary;
        private final PrintStream secondary;

        TeeOutputStream(PrintStream primary, PrintStream secondary) {
            this.primary = primary;
            this.secondary = secondary;
        }

        @Override
        public void write(int b) throws IOException {
            primary.write(b);
            secondary.write(b);
        }

        @Override
        public void write(byte[] buf, int off, int len) throws IOException {
            primary.write(buf, off, len);
            secondary.write(buf, off, len);
        }

        @Override
        public void flush() throws IOException {
            primary.flush();
            secondary.flush();
        }
    }
}
