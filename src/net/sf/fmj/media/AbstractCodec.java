package net.sf.fmj.media;

import javax.media.*;

import net.sf.fmj.utility.*;

/**
 * Abstract base class to implement Codec.
 * 
 * @author Ken Larson
 * 
 */
public abstract class AbstractCodec extends AbstractPlugIn implements Codec
{
    protected Format inputFormat = null;
    protected Format outputFormat = null;
    protected boolean opened = false;
    protected Format[] inputFormats = new Format[0];

    protected boolean checkInputBuffer(Buffer b)
    {
        return true; // TODO
    }

    protected final void dump(String label, Buffer buffer)
    {
        System.out.println(label + ": "
                + LoggingStringUtils.bufferToStr(buffer));

    }

    protected Format getInputFormat()
    {
        return inputFormat;
    }

    protected Format getOutputFormat()
    {
        return outputFormat;
    }

    public Format[] getSupportedInputFormats()
    {
        return inputFormats;
    }

    public abstract Format[] getSupportedOutputFormats(Format input);

    protected boolean isEOM(Buffer b)
    {
        return b.isEOM();
    }

    public abstract int process(Buffer input, Buffer output);

    protected void propagateEOM(Buffer b)
    {
        b.setEOM(true);
    }

    public Format setInputFormat(Format format)
    {
        if (matches(format, getSupportedInputFormats()) != null)
        {
            Log.comment("Setting input format on AbstractCodec to " + format);
            this.inputFormat = format;
            return inputFormat;
        }

        Log.info("AbstractCodec doesn't support format " + format);
        return null;
    }

    public Format setOutputFormat(Format format)
    {
        if (matches(format, getSupportedOutputFormats(this.inputFormat)) != null)
        {
            Log.comment("Setting output format on AbstractCodec to " + format);
            this.outputFormat = format;
            return outputFormat;
        }

        Log.info("AbstractCodec doesn't support format " + format);
        return null;
    }

    /**
     * Utility to perform format matching.
     *
     * @param in input format
     * @param outs array of output formats
     * @return the first output format that is supported
     */
    public static Format matches(Format in, Format outs[])
    {
        for (Format out : outs)
        {
            if (in.matches(out))
            {
                return out;
            }
        }
        return null;
    }
}
