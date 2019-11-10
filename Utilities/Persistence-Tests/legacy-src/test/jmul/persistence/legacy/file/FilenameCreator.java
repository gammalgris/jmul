/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.legacy.file;


import java.io.File;


/**
 * An implementation of a name creator.
 *
 * @author Kristian Kutin
 */
public class FilenameCreator implements NameCreator {

    /**
     * The file separator for the underlying operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A placeholder.
     */
    private static final String PLACEHOLDER = "%no%";

    /**
     * A template name.
     */
    private static final String FILENAME_TEMPLATE = "test%no%.xml";

    /**
     * A template name.
     */
    private static final String SUBDIRECTORY_TEMPLATE = "bucket%no%";

    /**
     * A bucket (subdirectory) size.
     */
    private static final long maxBucketSize = 1000L;

    /**
     * A base directory.
     */
    private final String basedir;

    /**
     * Counts the total number of generated filenames.
     */
    private long fileCounter;

    /**
     * Counts the total number of generated subdirectory names.
     */
    private long directoryCounter;

    /**
     * Counts the size of the current bucket.
     */
    private long bucketCounter;

    /**
     * Constructs a filename creator.
     *
     * @param aBasedir
     */
    public FilenameCreator(String aBasedir) {

        basedir = aBasedir;

        fileCounter = 0L;
        directoryCounter = 0L;
        bucketCounter = 0L;
    }

    /**
     * Create a directory name.
     *
     * @return a directory name
     */
    private String createDirectoryName() {

        String subdirectory = SUBDIRECTORY_TEMPLATE;
        subdirectory = subdirectory.replace(PLACEHOLDER, String.valueOf(directoryCounter));

        String directory = basedir + FILE_SEPARATOR + subdirectory;

        return directory;
    }

    /**
     * Creates a filename.
     *
     * @return a filename
     */
    private String createFilename() {

        String filename = FILENAME_TEMPLATE;
        filename = filename.replace(PLACEHOLDER, String.valueOf(fileCounter));

        return filename;
    }

    /**
     * Creates a name.
     *
     * @return a name
     */
    public String createName() {

        String filename = createFilename();
        String directoy = createDirectoryName();

        File dir = new File(directoy);
        dir.mkdirs();

        String name = directoy + FILE_SEPARATOR + filename;


        fileCounter++;
        bucketCounter++;


        if (bucketCounter >= maxBucketSize) {

            bucketCounter = 0L;
            directoryCounter++;
        }

        return name;
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        NameCreator creator = new FilenameCreator("D:\\test");

        for (int a = 0; a < 2000; a++) {

            System.out.println(creator.createName());
        }

        System.out.println("done.");
    }

}
