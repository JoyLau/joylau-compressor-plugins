package cn.joylau.code;

import org.apache.maven.plugin.AbstractMojo;

import java.io.File;

/**
 * @goal make-win-service
 * @phase package
 */
public class ResourcesCompressorMojo extends AbstractMojo {
    /**
     * @parameter property="project.build.directory"
     * @required
     */
    private File targetDir;

    /**
     * @parameter property="project.basedir"
     * @required
     * @readonly
     */
    private File baseDir;
    /**
     * @parameter property="project.build.sourceDirectory"
     * @required
     * @readonly
     */
    private File sourceDir;
    /**
     * @parameter property="project.build.testSourceDirectory"
     * @required
     * @readonly
     */
    private File testSourceDir;

    /** @parameter property="project.groupId"
     *  @required
     */
    private String groupId;

    /** @parameter property="project.artifactId"
     *  @required
     */
    private String artifactId;

    /** @parameter property="project.version"
     *  @required
     */
    private String version;

    /** @parameter property="project.description"
     */
    private String description;

    /**
     * @parameter property="arguments"
     */
    private String[] arguments;



    public void execute() {
    }
}
