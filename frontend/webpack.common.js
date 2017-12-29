var path = require("path");

module.exports = {
    //entry: path.resolve(__dirname, "src/main/js/index.js"),
    entry: path.resolve(__dirname, "build/classes/kotlin/main/min/frontend.js"),
    output: {
        path: path.resolve(__dirname, "build/web"),
        filename: "bundle.js"
    },
    resolve: {
        modules: [path.resolve(__dirname, "node_modules"),
            path.resolve(__dirname, "build/classes/kotlin/main/min/"),
            path.resolve(__dirname, "build/web")]
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: ["source-map-loader"],
                enforce: "pre"
            },
            {
                test: /\.(png|jpg|gif|svg)$/,
                loader: 'file-loader'
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            }
        ]
    }
};