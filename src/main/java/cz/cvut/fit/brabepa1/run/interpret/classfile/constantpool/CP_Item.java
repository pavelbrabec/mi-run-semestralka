package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

/**
 *
 * @author pavel
 */
public class CP_Item {

    ConstantPool constantPool;
    Tag tag;

    public CP_Item(ConstantPool constantPool, Tag tag) {
        this.constantPool = constantPool;
        this.tag = tag;
    }

    public enum Tag {
        CLASS(7),
        FIELDREF(9),
        METHODREF(10),
        INTERFACEMETHODREF(11),
        STRING(8),
        INTEGER(3),
        FLOAT(4),
        LONG(5),
        DOUBLE(6),
        NAMEANDTYPE(12),
        UTF8(1),
        METHODHANDLE(15),
        METHODTYPE(16),
        INVOKEDYNAMIC(18);

        private final int value;

        Tag(int tag) {
            this.value = tag;
        }

        public static Tag getTag(int number) {
            for (Tag tag : Tag.values()) {
                if (tag.getValue() == number) {
                    return tag;
                }
            }
            return null;
        }

        public int getValue() {
            return this.value;
        }
    }
}
