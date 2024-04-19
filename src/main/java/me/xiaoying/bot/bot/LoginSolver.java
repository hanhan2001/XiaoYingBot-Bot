package me.xiaoying.bot.bot;

import kotlin.coroutines.Continuation;
import me.xiaoying.bot.core.Xyb;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.auth.QRCodeLoginListener;
import net.mamoe.mirai.utils.DeviceVerificationRequests;
import net.mamoe.mirai.utils.DeviceVerificationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginSolver extends net.mamoe.mirai.utils.LoginSolver implements QRCodeLoginListener {
    @Override
    public void onFetchQRCode(@NotNull Bot bot, @NotNull byte[] bytes) {
        try {
            OutputStream outputStream = Files.newOutputStream(Paths.get("./"));
            outputStream.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStateChanged(@NotNull Bot bot, @NotNull QRCodeLoginListener.State state) {

    }

    @Override
    public int getQrCodeEcLevel() {
        return QRCodeLoginListener.super.getQrCodeEcLevel();
    }

    @Override
    public int getQrCodeMargin() {
        return QRCodeLoginListener.super.getQrCodeMargin();
    }

    @Override
    public int getQrCodeSize() {
        return QRCodeLoginListener.super.getQrCodeSize();
    }

    @Override
    public long getQrCodeStateUpdateInterval() {
        return QRCodeLoginListener.super.getQrCodeStateUpdateInterval();
    }

    @NotNull
    @Override
    public QRCodeLoginListener createQRCodeLoginListener(@NotNull Bot bot) {
        return new QRCodeLoginListener() {
            @Override
            public void onStateChanged(@NotNull Bot bot, @NotNull QRCodeLoginListener.State state) {
                Xyb.getLogger().info("账号: &e{} &f当前状态: &e{}", String.valueOf(bot.getId()), state.toString());
            }

            @Override
            public void onFetchQRCode(@NotNull Bot bot, @NotNull byte[] bytes) {
                try {
                    OutputStream outputStream = Files.newOutputStream(Paths.get("C:/Users/Administrator/Desktop/123.png"));
                    outputStream.write(bytes, 0, bytes.length);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Nullable
    @Override
    public Object onSolvePicCaptcha(@NotNull Bot bot, @NotNull byte[] bytes, @NotNull Continuation<? super String> continuation) {
        return null;
    }

    @Nullable
    @Override
    public Object onSolveSliderCaptcha(@NotNull Bot bot, @NotNull String s, @NotNull Continuation<? super String> continuation) {
        return null;
    }

    @Nullable
    @Override
    public Object onSolveDeviceVerification(@NotNull Bot bot, @NotNull DeviceVerificationRequests requests, @NotNull Continuation<? super DeviceVerificationResult> $completion) {
        DeviceVerificationRequests.FallbackRequest fallback = requests.getFallback();
        String url = fallback.getUrl();//HTTP URL. 可能需要在 QQ 浏览器中打开并人工操作.
        System.out.println(url);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return super.onSolveDeviceVerification(bot, requests, $completion);
    }

    @Override
    public boolean isSliderCaptchaSupported() {
        return true;
    }
}