# 一款基于YUI Compressor 打包压缩 js css html 的Maven插件


## 怎么使用？
pom 中引入插件：
``` xml
    <plugin>
        <groupId>cn.joylau.code</groupId>
        <artifactId>joylau-compressor-plugins</artifactId>
        <version>1.2.RELEASE</version>
        <executions>
            <execution>
                <id>resource-compressor</id>
                <phase>compile</phase>
                <goals>
                    <goal>resource-compressor</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <cssConfigs>
                <cssConfig>
                    <dir>/static/css</dir>
                    <include>*.css</include>
                    <exclude>*.min.css</exclude>
                </cssConfig>
            </cssConfigs>
            <jsConfigs>
                <jsConfig>
                    <dir>/static/js</dir>
                    <include>*.js</include>
                    <exclude>*.min.js</exclude>
                </jsConfig>
            </jsConfigs>
            <htmlConfigs>
                <htmlConfig>
                    <dir>/templates</dir>
                    <include>*.html</include>
                </htmlConfig>
            </htmlConfigs>
        </configuration>
    </plugin>
```

## 配置解释