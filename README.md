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
                    <munge>true</munge>
                </jsConfig>
            </jsConfigs>
            <htmlConfigs>
                <htmlConfig>
                    <dir>/templates</dir>
                    <include>*.html</include>
                    <removeIntertagSpaces>true</removeIntertagSpaces>
                    <compressJavaScript>false</compressJavaScript>
                    <compressCss>true</compressCss>
                </htmlConfig>
            </htmlConfigs>
        </configuration>
    </plugin>
```

## 配置解释
- `phase` : compile 表明该插件在 compile 时调用
- `goal` ： 固定为 resource-compressor 不需要改变
- `cssConfigs` , 可配置多个 cssConfig
    - cssConfig 
        - dir： css文件目录
        - include：包含的css文件，支持通配符
        - exclude：排除的css文件，支持通配符


- `jsConfigs` , 可配置多个 jsConfig
    - jsConfig 
        - dir： js文件目录
        - include：包含的js文件，支持通配符
        - exclude：排除的js文件，支持通配符
        - munge: 是否进行代码混淆，缺省值为 false
        - preserveAllSemiColons : 保留所有的分号，缺省值为 false
        - disableOptimizations : 禁用自带的所有优化措施，缺省值为 false


- `htmlConfigs` , 可配置多个 htmlConfig
    - htmlConfig 
        - dir： js文件目录
        - include：包含的js文件，支持通配符
        - exclude：排除的js文件，支持通配符
        - removeComments: 是否移除注释，缺省值为 true
        - removeIntertagSpaces : 是否移除标签之间的空格，缺省值为 false
        - compressJavaScript : 是否对html里的js代码进行压缩，缺省值为 false
        - compressCss : 是否对html里的css代码进行压缩，缺省值为 false
        
## 压缩信息
当看到以下图片所示的信息后，则压缩成功

![joylau-compressor-plugins](http://image.joylau.cn/blog/resource-compressor.png)

例如 ：[INFO] common.js(8.71KB==>4.58KB,47.39%)

表示 ：common.js 源文件大小8.71KB，压缩后大小 4.58KB，压缩率47.39%
